package aflammino_week10;

/** 
 * @Course: SDEV 350 ~ Java Programming I
 * @Author Name: Flammino
 * @Assignment Name: aflammino_week10
 * @Date: Apr 19, 2017
 * @Description: GUI that takes a user entered array or a randomly generated 
 * array of integers, sorts using a variety of methods, and times each method
 */

//Imports
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.*;
//Begin Class Aflammino_week10
public class Aflammino_week10 extends Application {
static ArrayList<Integer> unsorted = new ArrayList<>();
    static double sTime; //Start time
    static double eTime; //End timer

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Define panes */
        BorderPane bPane = new BorderPane();
        VBox topBox = new VBox();
        GridPane leftGrid = new GridPane();
        GridPane rightGrid = new GridPane(); // Needed for margins
        HBox bottomBox = new HBox(15);

        /* Set VBox attributes */
        topBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        topBox.setAlignment(Pos.CENTER);

        /* Set HBox attributes */
        bottomBox.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        bottomBox.setAlignment(Pos.CENTER);

        /* Set GridPane Attributes */
        leftGrid.setAlignment(Pos.CENTER_LEFT);
        leftGrid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        leftGrid.setHgap(5.5);
        leftGrid.setVgap(5.5);
        rightGrid.setAlignment(Pos.CENTER_LEFT);
        rightGrid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        rightGrid.setHgap(5.5);
        rightGrid.setVgap(20);

        /* Add panes to BorderPanes, set borderpane attributes */
        bPane.setTop(topBox);
        bPane.setLeft(leftGrid);
        bPane.setBottom(bottomBox);

        /* Declare top vBox components */
        Text banner = new Text("Sortermatic");
        banner.setFont(Font.font("Bodoni MT Black", FontWeight.BOLD,
                FontPosture.ITALIC, 28));
        Text instructions = new Text("Click Create Array or enter an array of "
                + "numbers separated by spaces\nor click " +
                "Create Array then click Sort");
        instructions.setTextAlignment(TextAlignment.CENTER);
        topBox.getChildren().add(banner);
        topBox.getChildren().add(instructions);

        /* Add left GridPane components */
        Label lblOriginal = new Label("Original Array:");
        Label lblInsertionSort = new Label("Insertion Sort: ");
        Label lblBubbleSort = new Label("Bubble Sort: ");
        Label lblMergeSort = new Label("Merge Sort: ");
        Label lblQuickSort = new Label("Quick Sort: ");
        Label lblHeapSort = new Label("Heap Sort: ");
        Label lblBucketSort = new Label("Bucket Sort: ");
        Label lblRadixSort = new Label("Radix Sort: ");
        TextField txtOriginal = new TextField();
        TextArea txaInsertionSort = new TextArea();
        TextArea txaBubbleSort = new TextArea();
        TextArea txaMergeSort = new TextArea();
        TextArea txaQuickSort = new TextArea();
        TextArea txaHeapSort = new TextArea();
        TextArea txaBucketSort = new TextArea();
        TextArea txaRadixSort = new TextArea();
        txtOriginal.setPrefWidth(340);
        txaInsertionSort.setPrefHeight(30);
        txaInsertionSort.setEditable(false);
        txaBubbleSort.setPrefHeight(30);
        txaBubbleSort.setEditable(false);
        txaMergeSort.setPrefHeight(30);
        txaMergeSort.setEditable(false);
        txaQuickSort.setPrefHeight(30);
        txaQuickSort.setEditable(false);
        txaHeapSort.setPrefHeight(30);
        txaHeapSort.setEditable(false);
        txaBucketSort.setPrefHeight(30);
        txaRadixSort.setPrefHeight(30);
        txaRadixSort.setEditable(false);
        leftGrid.add(lblOriginal, 0, 0);
        leftGrid.add(lblInsertionSort, 0, 1);
        leftGrid.add(lblBubbleSort, 0, 2);
        leftGrid.add(lblMergeSort, 0, 3);
        leftGrid.add(lblQuickSort, 0, 4);
        leftGrid.add(lblHeapSort, 0, 5);
        leftGrid.add(lblBucketSort, 0, 6);
        leftGrid.add(lblRadixSort, 0, 7);
        leftGrid.add(txtOriginal, 1, 0);
        leftGrid.add(txaInsertionSort, 1, 1);
        leftGrid.add(txaBubbleSort, 1,2);
        leftGrid.add(txaMergeSort, 1,3);
        leftGrid.add(txaQuickSort,1,4);
        leftGrid.add(txaHeapSort,1,5);
        leftGrid.add(txaBucketSort,1,6);
        leftGrid.add(txaRadixSort,1,7);

        /* Add bottom hBox components */
        Button btnCreateArray = new Button("Create Array");
        Button btnSort = new Button ("Sort");
        Button btnClear = new Button("Clear");
        Button btnExit = new Button("Exit");
        bottomBox.getChildren().add(btnCreateArray);
        bottomBox.getChildren().add(btnSort);
        bottomBox.getChildren().add(btnClear);
        bottomBox.getChildren().add(btnExit);

        /* Creates scene, sets stage */
        Scene scene = new Scene(bPane);
        primaryStage.setTitle("Sorter");
        primaryStage.setScene(scene);
        primaryStage.show();

        Validation v = new Validation();

        btnCreateArray.setOnAction((ActionEvent e) ->{
            String array = txtOriginal.getText();
            if (!v.emptyString(array)){
                arrayExists();
            }
            else{
                unsorted.clear(); // Clears old array in case user hasn't hit clear button
                Random rand = new Random();
                for (int i = 0; i < 13; i++){
                    int r = rand.nextInt(101);
                    unsorted.add(r);
                }
                arrayToTextField(unsorted, txtOriginal);
                          }
        });

        btnSort.setOnAction((ActionEvent e) ->{
            try {
                String array = txtOriginal.getText();
                if (v.emptyArrayList(unsorted)) {
                    if (v.emptyString(array)) {
                        noArray(txtOriginal);
                    } else {
                        unsorted = readList(array);
                    }
                }
                /** Clears text areas if user hasn't hit the clear button after 
                 * running. Allows user to run the same
                 * unsorted array multiple times to get average run times.
                 */
                if (!v.emptyTextArea(txaInsertionSort)) {
                    clearTextAreas(txaBubbleSort, txaBucketSort, txaHeapSort, 
                            txaInsertionSort, txaMergeSort, txaQuickSort,
                            txaRadixSort);
                }
                if (!v.emptyTextField(txtOriginal)) { // Prevents times from 
                    //being counted if there is nothing to sort
                    int size = unsorted.size() - 1;
                    ArrayList<Integer> tempArr = (ArrayList<Integer>) unsorted.clone();
                    double tResult;
                    Sort s = new Sort(tempArr);
                    sTime = System.nanoTime();
                    s.insertion(tempArr);
                    eTime = System.nanoTime();
                    tResult = eTime - sTime;
                    arrayToTextArea(tempArr, txaInsertionSort);
                    txaInsertionSort.appendText("\nTook " + tResult + " "
                            + "nanoseconds to run.");
                    tempArr = (ArrayList<Integer>) unsorted.clone();
                    sTime = System.nanoTime();
                    s.bubble(tempArr);
                    eTime = System.nanoTime();
                    tResult = eTime - sTime;
                    arrayToTextArea(tempArr, txaBubbleSort);
                    txaBubbleSort.appendText("\nTook " + tResult + " "
                            + "nanoseconds to run.");
                    tempArr = (ArrayList<Integer>) unsorted.clone();
                    sTime = System.nanoTime();
                    s.mergeSort(tempArr);
                    eTime = System.nanoTime();
                    tResult = eTime - sTime;
                    arrayToTextArea(tempArr, txaMergeSort);
                    txaMergeSort.appendText("\nTook " + tResult + 
                            " nanoseconds to run.");
                    tempArr = (ArrayList<Integer>) unsorted.clone();
                    sTime = System.nanoTime();
                    s.qSort(tempArr, 0, size);
                    eTime = System.nanoTime();
                    tResult = eTime - sTime;
                    arrayToTextArea(tempArr, txaQuickSort);
                    txaQuickSort.appendText("\nTook " + tResult + 
                            " nanoseconds to run.");
                    tempArr = (ArrayList<Integer>) unsorted.clone();
                    sTime = System.nanoTime();
                    s.hSort(tempArr);
                    eTime = System.nanoTime();
                    tResult = eTime - sTime;
                    arrayToTextArea(tempArr, txaHeapSort);
                    txaHeapSort.appendText("\nTook " + tResult + 
                            " nanoseconds to run.");
                    tempArr = (ArrayList<Integer>) unsorted.clone();
                    int minIndex = Collections.min(tempArr);
                    int max = Collections.max(tempArr);
                    sTime = System.nanoTime();
                    tempArr = s.bucketSort(tempArr, minIndex, max);
                    eTime = System.nanoTime();
                    tResult = eTime - sTime;
                    arrayToTextArea(tempArr, txaBucketSort);
                    txaBucketSort.appendText("\nTook " + tResult + 
                            " nanoseconds to run.");
                    tempArr = (ArrayList<Integer>) unsorted.clone();
                    sTime = System.nanoTime();
                    s.radixSort(tempArr, max);
                    eTime = System.nanoTime();
                    tResult = eTime - sTime;
                    arrayToTextArea(tempArr, txaRadixSort);
                    txaRadixSort.appendText("\nTook " + tResult + 
                            " nanoseconds to run.");
                    unsorted.clear(); // Allows user to manually enter values in 
                    //ArrayList for subsequent runs
                    tempArr.clear();
                    s = null;
                }
            }
                catch(NumberFormatException | NullPointerException | NoSuchElementException ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Problem Parsing array!");
                    alert.setContentText("Please ensure that the array you "
                            + "entered has a single space between integers.");
                    alert.showAndWait();
                }
            });

        btnClear.setOnAction((ActionEvent e) -> {
            txtOriginal.clear();
            clearTextAreas(txaBubbleSort, txaBucketSort, txaHeapSort, 
                    txaInsertionSort, txaMergeSort, txaQuickSort,
                    txaRadixSort);

        });

        btnExit.setOnAction((ActionEvent e) -> {
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("Goodbye!");
            exit.setContentText("Really quit?");
            Optional<ButtonType> result = exit.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            }
        });
    }
    private ArrayList<Integer> readList(String array) {
        ArrayList<Integer> intList = new ArrayList<>(); //new arraylist
        List<String> myList = new ArrayList<>(Arrays.asList(array.split(" ")));  
         // ^remove spaces^
        //loop through String List, convert to Integers, and place into 
        //ArrayList of integers
            for (String s : myList) {
                intList.add(Integer.valueOf(s));
            }
            return intList; //return list


    }

    private void arrayExists(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Array already present!");
        alert.setContentText("Array already present. Please delete Original "
                + "array or hit the clear button and try again");
        alert.showAndWait();
    }

    private void noArray(TextField txtOriginal){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No array found!");
        alert.setContentText("Please enter a list of numbers separated by a "
                + "space in the box to the right of Original, " +
                "or click the Create Array Button");
        txtOriginal.requestFocus();
        alert.showAndWait();
    }

    private void arrayToTextField(ArrayList<Integer> a, TextField f){
        int size = a.size();
        for (int i = 0; i < size; i++){
            f.appendText(Integer.toString(a.get(i)));
            f.appendText(" ");
        }
    }

    private void arrayToTextArea(ArrayList<Integer> a, TextArea t){
        int size = a.size();
        for (int i = 0; i < size; i++){
            t.appendText(Integer.toString(a.get(i)));
            t.appendText(" ");
        }
    }

    private void clearTextAreas(TextArea a, TextArea b, TextArea c, TextArea d, 
            TextArea e, TextArea f, TextArea g){
        a.clear();
        b.clear();
        c.clear();
        d.clear();
        e.clear();
        f.clear();
        g.clear();
    }
}

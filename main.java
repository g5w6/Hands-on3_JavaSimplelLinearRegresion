import java.util.List;

public class main {
    public static void main(String[] args) {
        DataSet dataset = new DataSet();

        // Modelo usando el 100% de los datos
        System.out.println("Prueba con 100% del dataset: ");
        SimpleLinearRegression model100 = new SimpleLinearRegression(dataset.getAllData());
        model100.trainModel();
        printModelDetails(model100, dataset.getAllData());

        // Modelo con 70% entrenamiento y 30% prueba
        System.out.println("\nPrueba con 70% entrenamiento y 30% prueba:");
        List<double[]> trainingSet70 = dataset.getTrainingSet(0.7);
        List<double[]> testSet30 = dataset.getTestSet(0.3);
        SimpleLinearRegression model70_30 = new SimpleLinearRegression(trainingSet70);
        model70_30.trainModel();
        printModelDetails(model70_30, testSet30);

        // Modelo con 30% entrenamiento y 70% prueba
        System.out.println("\nPrueba con 30% entrenamiento y 70% prueba:");
        List<double[]> trainingSet30 = dataset.getTrainingSet(0.3);
        List<double[]> testSet70 = dataset.getTestSet(0.7);
        SimpleLinearRegression model30_70 = new SimpleLinearRegression(trainingSet30);
        model30_70.trainModel();
        printModelDetails(model30_70, testSet70);
    }

    // Método para imprimir los detalles del modelo (B0, B1, MSE, correlación, predicciones)
    public static void printModelDetails(SimpleLinearRegression model, List<double[]> testSet) {
        System.out.println("Ecuacion de la recta: y = " + model.getB1() + " + " + model.getB0());
        System.out.println("MSE: " + model.MSE(testSet));
        System.out.println("Correlacion: " + model.correlation());
        System.out.println("R-squared: " + model.RSquared(testSet));

        // Realizar y mostrar predicciones
        double[] batchSizesToPredict = {50, 100, 150, 30, 70};
        for (double batchSize : batchSizesToPredict) {
            System.out.println("\n Prediccion para Batch Size " + batchSize + ": " + model.predict(batchSize));
        }
    }
}

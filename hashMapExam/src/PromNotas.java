import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PromNotas {
    Scanner input = new Scanner(System.in);

    //instanciacion de hashmaps a usar:
    HashMap <String, Double> studentsGrades = new HashMap<>();

    //declaracion de cantidad de notas a promediar:
    final int cantidadNotas = 3;

    //proyeccion del menu para la eleccion del usuario:
    public void menu() {
        int option = 0;
        String mensaje = "\n            ####MENU DE OPCIONES####\n\n" +
                //el ingreso debe ser validado para que esté en un rango de 1-5
                "1. Ingreso notas de estudiantes.\n" +
                "2. Imprimir cantidad de estudiantes ingresados.\n" +
                "3. Imprimir la cantidad de notas ingresadas.\n" +
                "4. Estudiantes que GANARON la materia.\n" +
                //pierden cuando su promedio es menor a 3.5
                "5. Estudiantes que PERDIERON la materia.\n" +
                //cuando la nota final es mayor de 2.0
                "6. Estudiantes que PUEDEN recuperar.\n" +
                "7. Estudiantes que pierden SIN recuperar.\n" +
                "8. Promedio de las notas finales obtenidas.\n" +
                "9. Imprimir el listado de estudiantes y sus notas finales.\n" +
                "10. Salir\n";
        //validamos que mientras que la opcion no sea la de salir (10) el menú continúe ejecutandose
        do {
            System.out.println(mensaje);
            System.out.println("Ingresa la opción:");
            option = input.nextInt();
            validacionMenu(option);
        } while (option != 10);
    }
    //metodo para la proyeccion del menú que toma como argumento la opcion ingresada por el ususario
    private void validacionMenu(int option) {
        switch (option) {
            case 1:
                addGrades();
                break;
            case 2:
                if (vericarArrayVacio()) {
                    System.out.println("\nERROR: No se ha ingresado ningun dato.");
                    break;
                }
                System.out.println("\nLa cantidad de estudiantes ingresados son: " + countStudents());
                break;
            case 3:
                if (vericarArrayVacio()) {
                    System.out.println("\nERROR: No se ha ingresado ningun dato.");
                    break;
                }
                System.out.println("\nLa cantidad total de notas ingresadas son: " + countGrades());
                break;
            case 4:
                if (vericarArrayVacio()) {
                    System.out.println("\nERROR: No se ha ingresado ningun dato.");
                    break;
                }
                wonSubject();
                break;
            case 5:
                if (vericarArrayVacio()) {
                    System.out.println("\nERROR: No se ha ingresado ningun dato.");
                    break;
                }
                failedSubject();
                break;
            case 6:
                if (vericarArrayVacio()) {
                    System.out.println("\nERROR: No se ha ingresado ningun dato.");
                    break;
                }
                canRecover();
                break;
            case 7:
                if (vericarArrayVacio()) {
                    System.out.println("\nERROR: No se ha ingresado ningun dato.");
                    break;
                }
                cannotRecover();
                break;
            case 8:
                if (vericarArrayVacio()) {
                    System.out.println("\nERROR: No se ha ingresado ningun dato.");
                    break;
                }
                int cantidadNotas = countGrades();
                System.out.println("\nEl promedio de las notas finales obtenidas es: " + getPromedioFinalGrades(cantidadNotas));
                break;
            case 9:
                if (vericarArrayVacio()) {
                    System.out.println("\nERROR: No se ha ingresado ningun dato.");
                    break;
                }
                printStudentsGrades();
                break;
            case 10:
                System.out.println("         SALIENDO DEL SISTEMA...");
                break;
        }
    }
    //metodo que veridica si el hashmap se encuentra vacio y devuelve un valor booleano.
    private boolean vericarArrayVacio() {
        if (studentsGrades.isEmpty()) {
            return true;
        }
        return false;
    }

    //metodo que recibe como argumento la cantidad de notas y retorna el promedio de las notas
    private double getPromedioFinalGrades(int cantidadNotas) {
        double sum = 0;
        for (Double counterValue : studentsGrades.values()) {
            sum += counterValue;
        }
        return sum/cantidadNotas;
    }
    //metodo que imorime los estudiantes que no pueden recupererar, por ende pierden la materia
    private void cannotRecover() {
        System.out.println("Estudiantes que pierden la materia y no pueden recuperar: \n");

        Iterator<String> keyIterator = studentsGrades.keySet().iterator();

        while (keyIterator.hasNext()) {
            String keys = keyIterator.next();
            //key=keys || value: studentsGrades.get(keys);
            if (studentsGrades.get(keys) < 2.0) {
                System.out.println("- " + keys);
            }
        }
    }
    //metodo que imprime los estudiantes que perdieron la materia pero pueden recuperarla
    private void canRecover() {
        System.out.println("Estudiantes que perdieron pero pueden recuperar la materia: \n");

        Iterator<String> keyIterator = studentsGrades.keySet().iterator();

        while (keyIterator.hasNext()) {
            String keys = keyIterator.next();
            //key=keys || value: studentsGrades.get(keys);
            if (studentsGrades.get(keys) > 2.0 && studentsGrades.get(keys) < 3.5) {
                System.out.println("- " + keys);
            }
        }
    }
    //metodo que imprime las personas que perdieron la materia
    private void failedSubject() {
        System.out.println("Estudiantes que PERDIERON la materia: \n");

        Iterator<String> keyIterator = studentsGrades.keySet().iterator();

        while (keyIterator.hasNext()) {
            String keys = keyIterator.next();
            //key=keys || value: studentsGrades.get(keys);
            if (studentsGrades.get(keys) < 3.5) {
                System.out.println("- " + keys);
            }
        }
    }
    //metodo que imprime los estudiantes que ganaron la materia
    private void wonSubject() {
        System.out.println("Estudiantes que GANARON la materia: \n");

        Iterator<String> keyIterator = studentsGrades.keySet().iterator();

        while (keyIterator.hasNext()) {
            String keys = keyIterator.next();
            //key=keys || value: studentsGrades.get(keys);
            if (studentsGrades.get(keys) >= 3.5) {
                System.out.println("- " + keys);
            }
        }
    }
    //metodo que retorna la cantidad de notas ingresadas por los usuarios
    private int countGrades() {
        int sum = 0;
        for (double counterValue : studentsGrades.values()) {
            sum++;
        }
        return sum*cantidadNotas;
    }
    //metodo que retorna la cantidad de estudiantes ingresados en el sistema
    private int countStudents() {
        int sum = 0;
        for (String counterKey : studentsGrades.keySet()) {
            sum++;
        }
        return sum;
    }
    //metodo que imprime los datos ingresados
    private void printStudentsGrades() {
        System.out.println("            ####DATOS INGRESADOS####");
        for (Map.Entry<String, Double> entry : studentsGrades.entrySet()){
            System.out.println("\nESTUDIANTE: " + entry.getKey() + "\nNOTA FINAL: " + entry.getValue());
        }
    }
    //metodo que pide a los usuarios la cantidad de estudiantes a ingresar, las notas de cada uno de ellos
    //y los agrega como keys y values en el HashMap principal.
    private void addGrades() {
        System.out.println("\nIngresa la cantidad de estudiantes a ingresar sus notas: ");
        int cantidadEstudiantes = input.nextInt();

        for (int i = 0; i < cantidadEstudiantes; i++) {
            //obteniendo el nombre del estudiante (key)
            System.out.println("\nIngresa el nombre del estudiante " + (i+1));
            String name = input.next();

            //obteniendo el promedio del estudiante (value) :
            double notaFinal = getPromedioNotas(cantidadNotas);

            //añadiendo las keys y values del hashmap segun los datos obtenidos:
            studentsGrades.put(name, notaFinal);
        }
    }
    //metodo que recibe la cantidad de notas, pide las notas y retorna el promedio de las notas
    private double getPromedioNotas(int cantidadNotas) {
        double sum = 0;
        for (int i = 0; i < cantidadNotas; i++) {
            System.out.println("Ingresa la nota " + (i+1));
            double nota = input.nextDouble();
            if (nota < 0 || nota > 5) {
                System.out.println("Error: Ingresa una nota en el rango 1-5\n");
                i--;
                continue;
            }
            sum += nota;
        }
        return sum/cantidadNotas;
    }

}

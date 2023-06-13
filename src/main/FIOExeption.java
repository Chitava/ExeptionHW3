public class FIOExeption extends Throwable {


    private String minSpace = "Должно быть минимум два значения";
    private String maxSpace = "Вы вводите лишние данные, должно быть три параметра";

    public FIOExeption (int count) {
        if (count == 1){
            System.out.println(minSpace);
        }
        else {
            System.out.println(maxSpace);
        }

    }

}

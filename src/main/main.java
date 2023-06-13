/*Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные
пробелом:
Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:
фамилия, имя, отчество - строки
датарождения - строка формата dd.mm.yyyy
номертелефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m. Приложение должно проверить введенные данные по количеству. Если количество не совпадает
 с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных,
 чем требуется. Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать
встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с
информацией, что именно неверно. Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии,
 в него в одну строку должны записаться полученные данные, вида <Фамилия><Имя><Отчество><датарождения> <номертелефона>
 <пол> Однофамильцы должны записаться в один и тот же файл, в отдельные строки. Не забудьте закрыть соединение с файлом.
*/


import java.io.*;
import java.util.Arrays;


public class main {
    public static void main(String[] args) {
//        String name = InputName();
//        String data = InputData();
//        String phone = InputPhone();
//        String  sex = InputSex();
//        DataWriter(name, data, phone, sex);
        DataWriter("Петров", "03.06.2006", "89235632566", "f" );
    }
    public static String InputName() {
        int count = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (count < 1) {
            System.out.print("Введите фамилию имя отчество: ");
            try {
                String name = reader.readLine();
                for (int i = 0; i < name.length(); i++) {
                    if (name.charAt(i) == ' ') {
                        count++;
                    }
                }
                if (count < 1) {
                    throw new FIOExeption(1);
                }
                if (count > 2) {
                    count = 0;
                    throw new FIOExeption(2);
                }
                return name;
            } catch (FIOExeption e) {

            } catch (IOException e) {

            }
        }
        return null;
    }

    public static String InputData() {
        int count = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (count == 0) {
            System.out.println("Введите дату рождения в формате: дд мм гггг");
            try {
                String data = reader.readLine();
                int[] numArr = Arrays.stream(data.split(" ")).mapToInt(Integer::parseInt).toArray();
                count++;
                return data.replace(' ', '.');
            } catch (IOException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Вы вводите не корректные данные");
            }

        }
        return null;
    }


    public static String  InputPhone() {
        int count = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (count == 0) {
            System.out.print("Введите номер телефона: ");
            try {
                String  phoneNumb = reader.readLine();
                int phone = Integer.parseInt(phoneNumb);
                count++;
                return phoneNumb;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат данных");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


    public static String InputSex() {
        int count = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (count == 0) {
            System.out.println("Введите пол одной буквой, f - женский, m - мужской");
            try {
                String sex = reader.readLine();
                if (sex == "f") {
                    count++;
                    return sex;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    public static void DataWriter (String name, String date, String phoneNumber, String sex){
        String fileName = name+".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write("<" + name + ">" + "<" + date + ">" + "<" + phoneNumber + ">" + "<" + sex + ">");
                writer.append('\n');
                writer.flush();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }else{
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write("<" + name + ">" + "<" + date + ">" + "<" + phoneNumber + ">" + "<" + sex + ">");
                writer.append('\n');
                writer.flush();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}



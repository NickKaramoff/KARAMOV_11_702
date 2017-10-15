package ru.karamoff;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Channel.Show showSet1[] = {
                new Channel.Show(LocalTime.of(21, 0), LocalTime.of(21, 35), "Время"),
                new Channel.Show(LocalTime.of(12, 15), LocalTime.of(15, 0), "Время покажет"),
                new Channel.Show(LocalTime.of(9, 15), LocalTime.of(9, 50), "Контрольная закупка"),
                new Channel.Show(LocalTime.of(10, 55), LocalTime.of(12, 0), "Время")
        }; // первый

        Channel.Show showSet2[] = {
                new Channel.Show(LocalTime.of(11, 40), LocalTime.of(12, 0), "Вести. Местное время"),
                new Channel.Show(LocalTime.of(12, 0), LocalTime.of(13, 0), "Судьба человека"),
                new Channel.Show(LocalTime.of(13, 0), LocalTime.of(14, 0), "60 минут"),
                new Channel.Show(LocalTime.of(14, 0), LocalTime.of(14, 40), "Вести")
        }; // россия

        Channel.Show showSet3[] = {
                new Channel.Show(LocalTime.of(11, 0), LocalTime.of(12, 0), "Документальный проект"),
                new Channel.Show(LocalTime.of(12, 55), LocalTime.of(13, 0), "Казанская афиша"),
                new Channel.Show(LocalTime.of(13, 0), LocalTime.of(14, 10), "Загадки человечества с Олегом Шишкиным"),
                new Channel.Show(LocalTime.of(14, 10), LocalTime.of(16, 0), "Механик")
        }; // рен
        Channel.Show showSet4[] = {
                new Channel.Show(LocalTime.of(9, 40), LocalTime.of(12, 0), "Мистер и миссис Смит"),
                new Channel.Show(LocalTime.of(12, 55), LocalTime.of(13, 0), "Молодёжка. Взрослая жизнь"),
                new Channel.Show(LocalTime.of(13, 0), LocalTime.of(15, 0), "Восьмидесятые"),
                new Channel.Show(LocalTime.of(15, 0), LocalTime.of(18, 0), "Кухня")
        }; // стс
        Channel.Show showSet5[] = {
                new Channel.Show(LocalTime.of(11, 10), LocalTime.of(13, 0), "Адвокат"),
                new Channel.Show(LocalTime.of(13, 0), LocalTime.of(13, 25), "Сегодня"),
                new Channel.Show(LocalTime.of(13, 25), LocalTime.of(14, 0), "Обзор. Чрезвычайное происшествие"),
                new Channel.Show(LocalTime.of(14, 0), LocalTime.of(16, 0), "Место встречи")
        }; // нтв
        Channel.Show showSet6[] = {
                new Channel.Show(LocalTime.of(10, 30), LocalTime.of(12, 0), "Дом-2. Остров любви"),
                new Channel.Show(LocalTime.of(12, 0), LocalTime.of(14, 30), "Сашатаня"),
                new Channel.Show(LocalTime.of(14, 30), LocalTime.of(19, 0), "Интерны"),
                new Channel.Show(LocalTime.of(19, 0), LocalTime.of(20, 0), "Улица")
        }; // тнт
        Channel.Show showSet7[] = {
                new Channel.Show(LocalTime.of(11, 35), LocalTime.of(13, 35), "Футбол. Албания - Италия"),
                new Channel.Show(LocalTime.of(13, 35), LocalTime.of(13, 45), "Новости"),
                new Channel.Show(LocalTime.of(13, 45), LocalTime.of(15, 0), "Смешанные единоборства"),
                new Channel.Show(LocalTime.of(15, 0), LocalTime.of(15, 5), "Новости")
        }; // матч
        Channel.Show showSet8[] = {
                new Channel.Show(LocalTime.of(11, 30), LocalTime.of(12, 0), "Елена - принцесса Авалора"),
                new Channel.Show(LocalTime.of(12, 0), LocalTime.of(12, 30), "Алладин"),
                new Channel.Show(LocalTime.of(13, 30), LocalTime.of(13, 55), "Сорвиголова Кик Бутовски"),
                new Channel.Show(LocalTime.of(13, 55), LocalTime.of(15, 20), "Финес и Ферб")
        }; // дисней
        Channel.Show showSet9[] = {
                new Channel.Show(LocalTime.of(10, 10), LocalTime.of(12, 10), "Королевство полной луны"),
                new Channel.Show(LocalTime.of(12, 10), LocalTime.of(14, 30), "Лучшее во мне"),
                new Channel.Show(LocalTime.of(14, 30), LocalTime.of(17, 15), "Телохранитель"),
                new Channel.Show(LocalTime.of(17, 15), LocalTime.of(20, 10), "Последний самурай")
        }; // ТВ1000
        Channel.Show showSet10[] = {
                new Channel.Show(LocalTime.of(9, 0), LocalTime.of(12, 0), "Новости"),
                new Channel.Show(LocalTime.of(12, 0), LocalTime.of(15, 0), "Новости"),
                new Channel.Show(LocalTime.of(15, 0), LocalTime.of(18, 0), "Новости"),
                new Channel.Show(LocalTime.of(18, 0), LocalTime.of(21, 0), "Новости")
        };

        Channel channelSet[] = {
                new Channel(showSet1, "Первый"),
                new Channel(showSet2, "Россия"),
                new Channel(showSet3, "РЕН"),
                new Channel(showSet4, "СТС"),
                new Channel(showSet5, "НТВ"),
                new Channel(showSet6, "ТНТ"),
                new Channel(showSet7, "Матч"),
                new Channel(showSet8, "Дисней"),
                new Channel(showSet9, "ТВ1000"),
                new Channel(showSet10, "Евроньюс")

        }; // создание каналов и передач

        Television television = Television.getTelevision(channelSet);
        System.out.println("Введите, разделяя пробелом: название, серийный номер, марку, производителя, год срока службы, месяц срока службы, день срока службы пульта");
        Remote remote = Remote.builder()
                .attachedTelevision(television)
                .remoteName(scanner.next())
                .serialNumber(scanner.next())
                .brandName(scanner.next())
                .manufacturer(scanner.next())
                .lifetime(LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
                .build();

        while (true) {
            System.out.println("1. Вывести список каналов");
            System.out.println("2. Посмотреть, что идёт");
            System.out.println("3. Программа передач канала");
            System.out.println("4. Выход");
            System.out.println();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    television.printChannelSet();
                    break;
                case 2:
                    String channelToSwitch = scanner.next();
                    remote.switchTo(channelToSwitch);
                    break;
                case 3:
                    String channelToDisplaySchedule = scanner.next();
                    remote.scheduleOf(channelToDisplaySchedule);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Введите число от 1 до 4");
                    break;
            }
        }
    }
}

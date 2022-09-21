import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.*;
import java.util.regex.*;

public class SimpleBot implements LongPollingBot {
    final String[] COMMON_PHRASES = {
            "��� ������ ������ ����, ��������� � ����� � �� �������.",
            "����� �������� ����� ������� ������, ������ ���� ����.",
            "����� ��� ��� ������/�������� ������ ����� ��������.",
            "�������� � ��������� ���� ������� � ������� ����.",
            "������� ����� ����� ��� ��������������� ������.",
            "����������� ���� ������� ���������������� ���.",
            "����� ����� ������, �� ����� � ��������.",
            "��������� �����, �� ��������� �� ����.",
            "��� ���� ������, ��� ���� ��������.",
            "����� �� ���-�� �� �������������."};
    final String[] ELUSIVE_ANSWERS = {
            "������ ���������, ����� ����-��� �� ��������.",
            "�� ������, ��� ���������� ����� �����������.",
            "����� ����� ��������� � ���-�� ������?",
            "��������, �� ��� ����� ������ ������.",
            "�� ������, ��� ��� ���������� �����.",
            "��������, � ��� ����� �� ��� �����.",
            "�� ������������� ������ ��� �����?",
            "������, �� ��� ���������� ����.",
            "����� ��� ����� ����������?",
            "������� �������� �������?"};
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{
        // hello
        put("���", "hello");
        put("������", "hello");
        put("�������", "hello");
        put("����������", "hello");
        // who
        put("���\\s.*��", "who");
        put("��\\s.*���", "who");
        // name
        put("���\\s.*�����", "name");
        put("���\\s.*���", "name");
        put("����\\s.*���", "name");
        put("�����\\s.*���", "name");
        // howareyou
        put("���\\s.*����", "howareyou");
        put("���\\s.*�����", "howareyou");
        // whatdoyoudoing
        put("�����\\s.*���", "whatdoyoudoing");
        put("�����\\s.*�����", "whatdoyoudoing");
        put("���\\s.*�������", "whatdoyoudoing");
        put("���\\s.*�����������", "whatdoyoudoing");
        // whatdoyoulike
        put("���\\s.*��������", "whatdoyoulike");
        put("���\\s.*������", "whatdoyoulike");
        // iamfeelling
        put("�������", "iamfeelling");
        put("��������", "iamfeelling");
        put("���������", "iamfeelling");
        // yes
        put("^��", "yes");
        put("��������", "yes");
        // whattime
        put("�������\\s.*���", "whattime");
        put("�������\\s.*�����", "whattime");
        // bye
        put("������", "bye");
        put("��������", "bye");
        put("��\\s.*��������", "bye");
    }};
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{
        put("hello", "������������, ��� ��� ������.");
        put("who", "� ������� ���-���.");
        put("name", "������ ���� ������ :)");
        put("howareyou", "�������, ��� �������������. � ���� �� ������.");
        put("whatdoyoudoing", "� ������ �������� � ������.");
        put("whatdoyoulike", "��� ��������� ������ ��� � �� ������ ���������.");
        put("iamfeelling", "��� ����� ��� ��������? ���������� ���� ���������.");
        put("yes", "�������� ���� ������� ��� ������ ������������� ������.");
        put("bye", "�� ��������. �������, ��� ��������.");
    }};
    Pattern pattern; // for regexp
    Random random; // for random answers
    Date date; // for date and time

    public SimpleBot() {
        random = new Random();
        date = new Date();
    }

    public String sayInReturn(String msg, boolean ai) {
        String say = (msg.trim().endsWith("?"))?
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
                COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
        if (ai) {
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
                pattern = Pattern.compile(o.getKey());
                if (pattern.matcher(message).find())
                    if (o.getValue().equals("whattime")) return date.toString();
                    else return ANSWERS_BY_PATTERNS.get(o.getValue());
            }
        }
        return say;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        LongPollingBot.super.onUpdatesReceived(updates);
    }

    @Override
    public BotOptions getOptions() {
        return new BotOptions() {
            @Override
            public String getBaseUrl() {
                return Utils.getBotName();
            }
        };
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {

    }

    @Override
    public void onClosing() {
        LongPollingBot.super.onClosing();
    }

    @Override
    public String getBotUsername() {
        return Utils.getBotName();
    }

    @Override
    public String getBotToken() {
        return Utils.getToken();
    }

    @Override
    public void onRegister() {
        LongPollingBot.super.onRegister();
    }
}
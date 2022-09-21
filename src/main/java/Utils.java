import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    private  static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream(
                    "D:\\Java\\Ex_Files_Java_EE_JSP\\Exercise Files\\Ch10\\10_06\\End\\TelegramBot\\src\\main\\resources\\local.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getToken(){
        return properties.getProperty("BOT_TOKEN");
    }
    public static String getBotName(){
        return properties.getProperty("BOT_NAME");
    }
    public static Long getChatId(){
        return Long.parseLong(properties.getProperty("CHART_ID"));
    }

    String token = properties.getProperty("BOT_TOKEN");
    long chatId = Long.parseLong(properties.getProperty("CHART_ID"));
    TelegramBot bot = new TelegramBot(token);
    SendResponse resp= bot.execute(new SendMessage(chatId, "Hello!"));
}

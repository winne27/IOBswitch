package de.fehngarten.iobswitch.data;

import java.util.ArrayList;

import static de.fehngarten.iobswitch.global.Settings.settingsMaxInst;

public class ConfigWorkBasket {

    public static String urlFhemjs;
    public static String urlFhempl;
    public static String fhemjsPW = "";
    public static ArrayList<ConfigWorkInstance> data;

    static {
        data = new ArrayList<>(settingsMaxInst);
        for (int i = 0; i < settingsMaxInst; i++) {
            data.add(new ConfigWorkInstance());
        }
    }

}

package challenge.lccode.geoquizz.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zizzy on 5.6.2017..
 */

public class Countries {
    private static Map<String, String> map;
    private static List<String> countryCodes;

   static {
        map = new LinkedHashMap<>();

        map.put("AF","Afghanistan");
        map.put("AL","Albania");
        map.put("DZ","Algeria");
        map.put("AS","American Samoa");
        map.put("AD","Andorra");
        map.put("AO","Angola");
        map.put("AI","Anguilla");
        map.put("AG","Antigua and Barbuda");
        map.put("AR","Argentina");
        map.put("AM","Armenia");
        map.put("AW","Aruba");
        map.put("AU","Australia");
        map.put("AT","Austria");
        map.put("AZ","Azerbaijan");
        map.put("BS","Bahamas");
        map.put("BH","Bahrain");
        map.put("BD","Bangladesh");
        map.put("BB","Barbados");
        map.put("BY","Belarus");
        map.put("BE","Belgium");
        map.put("BZ","Belize");
        map.put("BJ","Benin");
        map.put("BM","Bermuda");
        map.put("BT","Bhutan");
        map.put("BO","Bolivia");
        map.put("BA","Bosnia and Herzegovina");
        map.put("BW","Botswana");
        map.put("BR","Brazil");
        map.put("BN","Brunei Darussalam");
        map.put("BG","Bulgaria");
        map.put("BF","Burkina Faso");
        map.put("BI","Burundi");
        map.put("KH","Cambodia");
        map.put("CM","Cameroon");
        map.put("CA","Canada");
        map.put("CV","Cape Verde");
        map.put("CF","Central African Republic");
        map.put("TD","Chad");
        map.put("CL","Chile");
        map.put("CN","China");
        map.put("CX","Christmas Island");
        map.put("CO","Colombia");
        map.put("KM","Comoros");
        map.put("CG","Congo");
        map.put("CK","Cook Islands");
        map.put("CR","Costa Rica");
        map.put("HR","Croatia");
        map.put("CU","Cuba");
        map.put("CY","Cyprus");
        map.put("CZ","Czech Republic");
        map.put("DK","Denmark");
        map.put("DJ","Djibouti");
        map.put("DM","Dominica");
        map.put("DO","Dominican Republic");
        map.put("EC","Ecuador");
        map.put("EG","Egypt");
        map.put("SV","El Salvador");
        map.put("GQ","Equatorial Guinea");
        map.put("ER","Eritrea");
        map.put("EE","Estonia");
        map.put("ET","Ethiopia");
        map.put("FJ","Fiji");
        map.put("FI","Finland");
        map.put("FR","France");
        map.put("GF","French Guiana");
        map.put("PF","French Polynesia");
        map.put("GA","Gabon");
        map.put("GM","Gambia");
        map.put("GE","Georgia");
        map.put("DE","Germany");
        map.put("GH","Ghana");
        map.put("GI","Gibraltar");
        map.put("GR","Greece");
        map.put("GL","Greenland");
        map.put("GD","Grenada");
        map.put("GT","Guatemala");
        map.put("GG","Guernsey");
        map.put("GW","Guinea-Bissau");
        map.put("GY","Guyana");
        map.put("HT","Haiti");
        map.put("HN","Honduras");
        map.put("HU","Hungary");
        map.put("IS","Iceland");
        map.put("IO","British Indian Ocean Territory");
        map.put("ID","Indonesia");
        map.put("IR","Iran, Islamic Republic of");
        map.put("IQ","Iraq");
        map.put("IE","Ireland");
        map.put("IL","Israel");
        map.put("IT","Italy");
        map.put("JM","Jamaica");
        map.put("JP","Japan");
        map.put("JE","Jersey");
        map.put("JO","Jordan");
        map.put("KZ","Kazakhstan");
        map.put("KE","Kenya");
        map.put("KI","Kiribati");
        map.put("KP","Korea, Democratic People's Republic of");
        map.put("KW","Kuwait");
        map.put("KG","Kyrgyzstan");
        map.put("LA","Laos");
        map.put("LV","Latvia");
        map.put("LB","Lebanon");
        map.put("LS","Lesotho");
        map.put("LR","Liberia");
        map.put("LY","Libya");
        map.put("LI","Liechtenstein");
        map.put("LT","Lithuania");
        map.put("LU","Luxembourg");
        map.put("MK","Macedonia, the Former Yugoslav Republic of");
        map.put("MG","Madagascar");
        map.put("MW","Malawi");
        map.put("MY","Malaysia");
        map.put("MV","Maldives");
        map.put("ML","Mali");
        map.put("MT","Malta");
        map.put("MH","Marshall Islands");
        map.put("MQ","Martinique");
        map.put("MR","Mauritania");
        map.put("MU","Mauritius");
        map.put("YT","Mayotte");
        map.put("MX","Mexico");
        map.put("FM","Micronesia, Federated States of");
        map.put("MD","Moldova, Republic of");
        map.put("MC","Monaco");
        map.put("MN","Mongolia");
        map.put("ME","Montenegro");
        map.put("MS","Montserrat");
        map.put("MA","Morocco");
        map.put("MZ","Mozambique");
        map.put("MM","Myanmar");
        map.put("NA","Namibia");
        map.put("NR","Nauru");
        map.put("NP","Nepal");
        map.put("NL","Netherlands");
        map.put("NC","New Caledonia");
        map.put("NZ","New Zealand");
        map.put("NI","Nicaragua");
        map.put("NE","Niger");
        map.put("NG","Nigeria");
        map.put("NU","Niue");
        map.put("NF","Norfolk Island");
        map.put("NO","Norway");
        map.put("OM","Oman");
        map.put("PK","Pakistan");
        map.put("PW","Palau");
        map.put("PA","Panama");
        map.put("PG","Papua New Guinea");
        map.put("PY","Paraguay");
        map.put("PE","Peru");
        map.put("PH","Philippines");
        map.put("PL","Poland");
        map.put("PT","Portugal");
        map.put("PR","Puerto Rico");
        map.put("QA","Qatar");
        map.put("RE","Reunion");
        map.put("RO","Romania");
        map.put("RU","Russian Federation");
        map.put("RW","Rwanda");
        map.put("BL","Saint Barthelemy");
        map.put("SH","Saint Helena");
        map.put("KN","Saint Kitts and Nevis");
        map.put("LC","Saint Lucia");
        map.put("MF","Saint Martin (French part)");
        map.put("PM","Saint Pierre and Miquelon");
        map.put("VC","Saint Vincent and the Grenadines");
        map.put("SM","San Marino");
        map.put("ST","Sao Tome and Principe");
        map.put("SA","Saudi Arabia");
        map.put("SN","Senegal");
        map.put("RS","Serbia");
        map.put("SC","Seychelles");
        map.put("SL","Sierra Leone");
        map.put("SG","Singapore");
        map.put("SK","Slovakia");
        map.put("SI","Slovenia");
        map.put("SB","Solomon Islands");
        map.put("SO","Somalia");
        map.put("ZA","South Africa");
        map.put("SS","South Sudan");
        map.put("ES","Spain");
        map.put("LK","Sri Lanka");
        map.put("SR","Suriname");
        map.put("SZ","Swaziland");
        map.put("SE","Sweden");
        map.put("CH","Switzerland");
        map.put("SY","Syrian Arab Republic");
        map.put("TJ","Tajikistan");
        map.put("TZ","United Republic of Tanzania");
        map.put("TH","Thailand");
        map.put("TG","Togo");
        map.put("TO","Tonga");
        map.put("TN","Tunisia");
        map.put("TR","Turkey");
        map.put("TM","Turkmenistan");
        map.put("TC","Turks and Caicos Islands");
        map.put("TV","Tuvalu");
        map.put("UG","Uganda");
        map.put("UA","Ukraine");
        map.put("US","United States");
        map.put("GB","United Kingdom");
        map.put("VI","US Virgin Islands");
        map.put("UY","Uruguay");
        map.put("UZ","Uzbekistan");
        map.put("VU","Vanuatu");
        map.put("VA","Holy See (Vatican City State)");
        map.put("VE","Venezuela");
        map.put("VN","Vietnam");
        map.put("YE","Yemen");
        map.put("ZM","Zambia");
        map.put("ZW","Zimbabwe");

        countryCodes = new ArrayList<>();
        countryCodes.addAll(map.keySet());
    }

    public static String getNameByCode(String countryCode) {
        return map.get(countryCode);
    }

    public static String getNameByIndex(int index) {
        return getNameByCode(countryCodes.get(index));
    }


     public static String getKeyByIndex(int index) {
          return countryCodes.get(index);
     }


     public static Integer getSize() {
        return countryCodes.size();
    }

}

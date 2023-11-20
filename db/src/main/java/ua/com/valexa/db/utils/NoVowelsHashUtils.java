package ua.com.valexa.db.utils;

import ua.com.valexa.db.model.enums.LanguageCode;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;

public class NoVowelsHashUtils {

    private static final String NO_VOLWES_REGEX = "[-аАоОуУэЭєЄеЕыЫяЯёЁиИюЮіІїЇьЬъЪгГґҐйЙ'ʼ\\\\s\\\\p{Punct}]";
    public static String calcNoVowelsHash(PersonName personName) {
        if (LanguageCode.UA.equals(personName.getLanguageCode())) {
            return calcNoVolwesHash(
                    (personName.getLastName() == null ? "" : personName.getLastName()) +
                            (personName.getFirstName() == null ? "" : personName.getFirstName()) +
                            (personName.getPatronymicName() == null ? "" : personName.getPatronymicName())
            );
        } else if (LanguageCode.RU.equals(personName.getLanguageCode())) {
            return
                    calcNoVolwesHash(personName.getLastName() == null ? "" : personName.getLastName()) +
                            calcNoVolwesHashFnameRu(personName.getFirstName() == null ? "" : personName.getFirstName()) +
                            calcNoVolwesHashPnameRu(personName.getPatronymicName() == null ? "" : personName.getPatronymicName());
        } else {
            return null;
        }
    }

    private static String calcNoVolwesHash(String value) {
        String result = value.replaceAll("ММ", "");
        result = result.replaceAll("СС", "С");
        result = result.replaceAll("ЛЛ", "Л");
        result = result.replaceAll("НН", "Н");
        result = result.replaceAll("ПП", "П");
        result = result.replaceAll("ТТ", "Т");
        result = result.replaceAll("ВВ", "В");
        result = result.replaceAll("ДД", "Д");
        result = result.replaceAll("РР", "Р");
        result = result.replaceAll("ББ", "Б");
        result = result.replaceAll("ЖЖ", "Ж");

        result = result.replaceAll(NO_VOLWES_REGEX, "");

        return result;
    }

    private static String calcNoVolwesHashFnameRu(String value) {
        String result = value;
        result = result.replaceAll("НИКОЛАЙ", "МИКОЛА");
        result = result.replaceAll("КОНСТАНТИН", "КОСТЯНТИН");
        result = result.replaceAll("ФИЛИПП", "ПИЛИП");
        result = result.replaceAll("НИКИТА", "МИКИТА");
        result = result.replaceAll("ИОСИФ", "ЙОСИП");
        result = result.replaceAll("ТРОФИМ", "ТРОХИМ");
        result = result.replaceAll("ЕВСТАФИЙ", "ЄВСТАХІЙ");

        result = result.replaceAll("НАДЕЖДА", "НАДІЯ");
        result = result.replaceAll("ЕВФРОСИНИЯ", "ЄФРОСИНІЯ");
        result = result.replaceAll("КРИСТИНА", "ХРИСТИНА");
        result = result.replaceAll("ВАСИЛИСА", "ВАСИЛИНА");

        result = result.replaceAll("СС", "С");
        result = result.replaceAll("ЛЛ", "Л");
        result = result.replaceAll("НН", "Н");
        result = result.replaceAll("ПП", "П");
        result = result.replaceAll("ТТ", "Т");
        result = result.replaceAll("ВВ", "В");
        result = result.replaceAll("ДД", "Д");
        result = result.replaceAll("РР", "Р");
        result = result.replaceAll("ББ", "Б");
        result = result.replaceAll("ЖЖ", "Ж");

        result = result.replaceAll(NO_VOLWES_REGEX, "");

        return result;
    }

    private static String calcNoVolwesHashPnameRu(String value) {
        String result = value;
        result = result.replaceAll("НИКОЛА", "МИКОЛА");
        result = result.replaceAll("КОНСТАНТИН", "КОСТЯНТИН");
        result = result.replaceAll("ФИЛИП", "ПИЛИП");
        result = result.replaceAll("НИКИТ", "МИКИТ");
        result = result.replaceAll("ИОСИФ", "ЙОСИП");
        result = result.replaceAll("ТРОФИМ", "ТРОХИМ");
        result = result.replaceAll("ЕВСТАФ", "ЄВСТАХ");
        result = result.replaceAll("ЯКОВЛЕВНА", "ЯКІВНА");
        result = result.replaceAll("ЯКОВЛЕВИЧ", "ЯКОВИЧ");
        result = result.replaceAll("ПРОКОФЬЕВНА", "ПРОКОПІВНА");
        result = result.replaceAll("ПРОКОФЬЕВИЧ", "ПРОКОПОВИЧ");
        result = result.replaceAll("КУЗЬМИНИЧНА", "КУЗЬМІВНА");
        result = result.replaceAll("АФАНАС", "ОПАНАС");

        result = result.replaceAll("СС", "С");
        result = result.replaceAll("ЛЛ", "Л");
        result = result.replaceAll("НН", "Н");
        result = result.replaceAll("ПП", "П");
        result = result.replaceAll("ТТ", "Т");
        result = result.replaceAll("ВВ", "В");
        result = result.replaceAll("ДД", "Д");
        result = result.replaceAll("РР", "Р");
        result = result.replaceAll("ББ", "Б");
        result = result.replaceAll("ЖЖ", "Ж");

        result = result.replaceAll(NO_VOLWES_REGEX, "");

        return result;
    }
}

package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.RegionDTOLocaleAwareToString;

import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

/**
 * Created on 15/04/2016.
 *
 * @author Robin D'Haese
 */
public class RegionDtoLocaleAwareToString extends RegionDTO {

    public static final String LANUAGE_TAG_NL = "nl";
    public static final String LANUAGE_TAG_FR = "fr";
    public static final String LANUAGE_TAG_DE = "de";
    private Locale locale;
    private RegionDTO regionDTO;

    public RegionDtoLocaleAwareToString(Locale locale, RegionDTO regionDTO){
        this.locale = locale;
        this.regionDTO = regionDTO;
    }

    public static Collection<RegionDtoLocaleAwareToString> mapCollection(Collection<RegionDTO> regions, Locale locale){
        Collection<RegionDtoLocaleAwareToString> regionsDecorators = new ArrayList<>();
        for (RegionDTO regionDTO : regions){
            regionsDecorators.add(new RegionDtoLocaleAwareToString(locale, regionDTO));
        }
        return regionsDecorators;
    }

    public String getNameNl() {
        return regionDTO.getNameNl();
    }

    public void setNameNl(String nameNl) {
        regionDTO.setNameNl(nameNl);
    }

    public String getNameFr() {
       return regionDTO.getNameFr();
    }

    public void setNameFr(String nameFr) {
        regionDTO.setNameFr(nameFr);
    }

    public String getNameDe() {
        return regionDTO.getNameDe();
    }

    public void setNameDe(String nameDe) {
       regionDTO.setNameDe(nameDe);
    }

    public String getNameEn() {
       return regionDTO.getNameEn();
    }

    public void setNameEn(String nameEn) {
        regionDTO.setNameEn(nameEn);
    }

    public String getCode() {
        return regionDTO.getCode();
    }

    public void setCode(String code) {
        regionDTO.setCode(code);
    }

    @Override
    public String toString() {
        if (Locale.forLanguageTag(LANUAGE_TAG_NL).equals(locale)){
            return getNameNl();
        } else if(Locale.forLanguageTag(LANUAGE_TAG_FR).equals(locale)){
            return getNameFr();
        } else if(Locale.forLanguageTag(LANUAGE_TAG_DE).equals(locale)){
            return getNameDe();
        } else {
            return  getNameEn();
        }
    }
}

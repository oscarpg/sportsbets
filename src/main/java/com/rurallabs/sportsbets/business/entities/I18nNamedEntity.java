package com.rurallabs.sportsbets.business.entities;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

public interface I18nNamedEntity extends Serializable {

    public String getName(final Locale locale);
    
    public String getName();
    
    public Map<String, String> getNamesByLang();
    
}

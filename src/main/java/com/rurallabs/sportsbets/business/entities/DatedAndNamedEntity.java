package com.rurallabs.sportsbets.business.entities;

import java.util.Date;

public interface DatedAndNamedEntity extends I18nNamedEntity {

    public Date getDate();
    
}

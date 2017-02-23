package io.swagger.api.impl;

import java.util.ArrayList;
import java.util.List;

import vohmm.corpus.*;

/**
 * Created by yochai on 06/02/2017.
 */
public class Word {
    private String lemma;
    private String ner;
    private String text;
    private String gender;
    private String number;
    private String person;
    private String polarity;
    private String pos;
    private String posType;
    private List<String> prefixes = new ArrayList<>();
    private String tense;
    private String suffixFunction;
    private String suffixGender;
    private String suffixNumber;
    private String suffixPerson;
    private boolean isDefinite;

    public Word(TokenExt token){
        Anal anal = token._token.getSelectedAnal();
        AnalysisInterface bitmaskResolver = new BitmaskResolver(anal.getTag().getBitmask(), token._token.getOrigStr(), false);
        ner = token.getNER();
        text = token._token.getOrigStr();
        gender = bitmaskResolver.getGender();
        number = bitmaskResolver.getNumber();
        person = bitmaskResolver.getPerson();
        polarity = bitmaskResolver.getPolarity();
        pos = bitmaskResolver.getPOS();
        posType = bitmaskResolver.getPOSType();
        lemma = anal.getLemma().getBaseformStr();

            if(bitmaskResolver.hasPrefix()) {
            for (AffixInterface a : bitmaskResolver.getPrefixes()
                    ) {
                prefixes.add(a.getStr());
            }
        }

        tense = bitmaskResolver.getTense();
        if(bitmaskResolver.hasSuffix()) {
            suffixFunction = bitmaskResolver.getSuffixFunction();
            suffixGender = bitmaskResolver.getSuffixGender();
            suffixNumber = bitmaskResolver.getSuffixNumber();
            suffixPerson = bitmaskResolver.getSuffixPerson();
        }

        isDefinite = bitmaskResolver.isDefinite();



    }



}

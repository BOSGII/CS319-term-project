package com.bosgii.internshipmanagement.matcher;

import com.bosgii.internshipmanagement.enums.MatchType;

public class MatcherFactory {
    public IMatcher createMatcher(MatchType matchType){
        switch(matchType){
            case EQUAL:
                return new EqualMatcher();
            case RATIO:
                return new RatioMatcher();
            default:
                return null;
        }
    }
}

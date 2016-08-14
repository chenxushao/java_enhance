package com.chenxushao.guava.preconditions;

import java.util.List;

import com.google.common.base.Preconditions;

/**
 * Class to learn Preconditions
 */
public class PreconditionsLesson {

    public enum Weather {
        WINDY,
        RAINY,
        SHINY,
        DOWNPOUR,
        CLOUDY
    }

    public void getSomeSuntan(Weather weather) {
        Preconditions.checkState(weather.equals(Weather.SHINY), "Weather is not the best for a sunbath");
    }

    public void displayFootballTeamMembers(List<String> teamMembers) {
        Preconditions.checkNotNull(teamMembers, "Team can not be null");
        Preconditions.checkArgument(teamMembers.size() == 11, "Full team should consist of 11 players");
    }

}

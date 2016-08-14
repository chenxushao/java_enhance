package com.chenxushao.guava.preconditions;

import java.util.Arrays;

public class PreconditionsTest {

    PreconditionsLesson preconditionsLesson = new PreconditionsLesson();

    public void shouldThrowIllegalState() throws Exception {

       preconditionsLesson.getSomeSuntan(PreconditionsLesson.Weather.CLOUDY);
    }

//    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp =
//            "Team can not be null")
    public void shouldNotAcceptNullFootballTeam() throws Exception {

        // when
        preconditionsLesson.displayFootballTeamMembers(null);
    }

//    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp =
//            "Full team should consist of 11 players")
    public void shouldNotAcceptNotFullFootballTeam() throws Exception {

        // when
        preconditionsLesson.displayFootballTeamMembers(Arrays.asList("Casillas", "Pepe", "Ramos", "Marcelo"));
    }


}

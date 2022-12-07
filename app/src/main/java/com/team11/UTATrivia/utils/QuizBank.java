package com.team11.UTATrivia.utils;

import com.team11.UTATrivia.models.Quiz;

import java.util.ArrayList;


public class QuizBank {

    private static ArrayList<Quiz> category1QuizList;
    private static ArrayList<Quiz> category2QuizList;
    private static ArrayList<Quiz> category3QuizList;


    public QuizBank() {
        category1QuizList = new ArrayList<>();
        category2QuizList = new ArrayList<>();
        category3QuizList = new ArrayList<>();
        initQuizDatabase1();
        initQuizDatabase2();
        initQuizDatabase3();
    }


    public static ArrayList<Quiz> getCategory1QuizList() {
        return category1QuizList;
    }
    public static ArrayList<Quiz> getCategory2QuizList() {
        return category2QuizList;
    }
    public static ArrayList<Quiz> getCategory3QuizList() {
        return category3QuizList;
    }

    private void initQuizDatabase1() {
        Quiz quiz1 = new Quiz("What division does the men’s basketball at UTA play in?",
                2,
                "D2",
                "D1",
                "D3",
                "None of the above");

        Quiz quiz2 = new Quiz("What is the name of UTA’s mascot?",
                1,
                "Blaze",
                "Haze",
                "Comet",
                "None of the above");

        Quiz quiz3 = new Quiz("Where does UTA play basketball home games?",
                3,
                "AT&T Center",
                "American Airlines Center",
                "College Park Center",
                "None of the above");

        Quiz quiz4 = new Quiz("Where do UTA athletes go for weightlifting?",
                1,
                "Maverick Activities Center",
                "College Park Center",
                "Arlington Gym",
                "None of the above");

        Quiz quiz5 = new Quiz("How many Division 1 teams does UTA have?",
                3,
                "11",
                "3",
                "15",
                "None of the above");

        Quiz quiz6 = new Quiz("Which of the following is NOT a UTA sport?",
                3,
                "Softball",
                "Golf",
                "Archery",
                "None of the above");

        Quiz quiz7 = new Quiz("Who are the “Movin Mavs”?",
                2,
                "Swimming Team",
                "Wheelchair Basketball Team",
                "Wheelchair Volleyball Team",
                "None of the above");

        Quiz quiz8 = new Quiz("What year was the College Park Center built?",
                2,
                "2008",
                "2012",
                "2015",
                "None of the above");

        Quiz quiz9 = new Quiz("Where does the UTA Track and Field team practice?",
                3,
                "College Park Center",
                "Maverick Activities Center",
                "Maverick Stadium",
                "None of the above");

        Quiz quiz10 = new Quiz("What team plays in the nearby AT&T stadium?",
                2,
                "UTA Men’s Basketball",
                "Dallas Cowboys",
                "Dallas Mavericks",
                "None of the above");

        category1QuizList.add(quiz1);
        category1QuizList.add(quiz2);
        category1QuizList.add(quiz3);
        category1QuizList.add(quiz4);
        category1QuizList.add(quiz5);
        category1QuizList.add(quiz6);
        category1QuizList.add(quiz7);
        category1QuizList.add(quiz8);
        category1QuizList.add(quiz9);
        category1QuizList.add(quiz10);
    }

    private void initQuizDatabase2() {
        Quiz quiz1 = new Quiz("When was Ransom Hall built?",
                2,
                "1925",
                "1919",
                "1945",
                "None of the above");

        Quiz quiz2 = new Quiz(" When it originated in 1895 what was the name of UTA?",
                1,
                "Arlington College",
                "Maverick College",
                "North Texas College",
                "None of the above");

        Quiz quiz3 = new Quiz("How many students were there in Arlington College first class?",
                3,
                "150",
                "300",
                "75",
                "None of the above");

        Quiz quiz4 = new Quiz(" In 1913 the institution was renamed to?",
                1,
                "Arlington Training School",
                "Arlington Military School",
                "Arlington Science School",
                "None of the above");

        Quiz quiz5 = new Quiz("When was the first time minority students were allowed to enroll at UTA?",
                2,
                "Spring 1950",
                "Fall 1962",
                "Fall 1965",
                "None of the above");

        Quiz quiz6 = new Quiz("How much did it cost to build the Engineering Research Building?",
                1,
                "126 million",
                "150 million",
                "175 million",
                "None of the above");

        Quiz quiz7 = new Quiz("How much did it cost to build the SEIR( Science and Engineering Innovation and Research)?",
                3,
                "85 million",
                "122 million",
                "125 million",
                "None of the above");

        Quiz quiz8 = new Quiz(" Who became the first dean of the College of Science?",
                3,
                "William Meacham",
                "Samuel Thomas",
                "Peter Girardot",
                "None of the above");

        Quiz quiz9 = new Quiz("When was the Olympic torch passed through the UT Arlington campus?",
                2,
                "1980",
                "1984",
                "1988",
                "None of the above");

        Quiz quiz10 = new Quiz("What was the name of the cafe that existed before Connection cafe?",
                2,
                "Cafe Arlington",
                "Paradise Cafe",
                "Heaven cafe",
                "None of the above");

        category2QuizList.add(quiz1);
        category2QuizList.add(quiz2);
        category2QuizList.add(quiz3);
        category2QuizList.add(quiz4);
        category2QuizList.add(quiz5);
        category2QuizList.add(quiz6);
        category2QuizList.add(quiz7);
        category2QuizList.add(quiz8);
        category2QuizList.add(quiz9);
        category2QuizList.add(quiz10);
    }

    private void initQuizDatabase3() {
        Quiz quiz1 = new Quiz("Who of the following is NOT a UTA engineering professor?",
                3,
                "Ishfaq Ahmed",
                "Vassil Athitsos",
                "Dr. George Benson",
                "None of the above");

        Quiz quiz2 = new Quiz("How many square feet is the ERB?",
                1,
                "234,000",
                "423,000",
                "323,000",
                "None of the above");

        Quiz quiz3 = new Quiz("When did the University of Texas at Arlington establish the College of Engineering?",
                1,
                "1959",
                "1955",
                "1951",
                "None of the above");

        Quiz quiz4 = new Quiz("What does UTA stand for? ",
                2,
                "University of Texas at Austin",
                "University of Texas at Arlington ",
                "United Teams of America",
                "None of the above");

        Quiz quiz5 = new Quiz("Which of the following is not a required class for software engineering?",
                3,
                "Operating systems ",
                "Information security ",
                "Intro to Information systems",
                "None of the above");

        Quiz quiz6 = new Quiz("What percentage of engineering students are male at UTA ?",
                2,
                "50%",
                "75%",
                "90%",
                "None of the above");

        Quiz quiz7 = new Quiz("What percentage of engineering students are females at UTA?",
                2,
                "50%",
                "25%",
                "10%",
                "None of the above");

        Quiz quiz8 = new Quiz("What percentage of the student body is made up of international students? \n",
                1,
                "9.5%",
                "4.2%",
                "5%",
                "None of the above");

        Quiz quiz9 = new Quiz("How many students are taking engineering courses at UTA?",
                3,
                "9,318",
                "6,356",
                "7,400",
                "None of the above");

        Quiz quiz10 = new Quiz("What is the average GPA for engineering students at UTA?",
                3,
                "3.5",
                "2.5",
                "3.0",
                "None of the above");

        category3QuizList.add(quiz1);
        category3QuizList.add(quiz2);
        category3QuizList.add(quiz3);
        category3QuizList.add(quiz4);
        category3QuizList.add(quiz5);
        category3QuizList.add(quiz6);
        category3QuizList.add(quiz7);
        category3QuizList.add(quiz8);
        category3QuizList.add(quiz9);
        category3QuizList.add(quiz10);
    }
}

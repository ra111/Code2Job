package com.studyx.urgames.code2job

/**
 * Created by rahula on 11/03/18.
 */

class verbal_ques {

    val ans: Array<String>
        get() = answers.clone()
    val ques: Array<String>
        get() = questions.clone()

    companion object {
        var questions = arrayOf("However, the real challenge today is in unlearning which is much harder....", "Alex had never been happy with his Indian origins....", "Such a national policy will surely divide and never unite the people.....", "An essay which appeals chiefly to the intellect is Francis Bacon’s Of Studies.....", "The establishment of the Third Reich influenced events in American history by starting a chain of events which culminated in war between Germany and the United States....", "The two boars standing silently next to the large tree…………………….not seen by the two hunters.", "\tI………………………no idea until he told me.", "It seemed like this buffalo ..…………never washed before I washed him this morning.", "I………………………if I could but I won’t because I can’t.\n", "Bangalore…..……… record rainfall in the previous years and the year before that.", "To have settled one’s affairs is a very good preparation to leading the rest of one’s life without concern for the future.")
        var answers = arrayOf("A.\tHowever, the real challenge today is in unlearning which is much harder.\n" +
                " \tB.\tBut the new world of business behaves differently from the world in which we grew up.\n" +
                "C.\tLearning is important for both people and organization.\n" +
                "D.\tEach of us has ‘mental model that we’ve used over the years to make sense. \n" +
                "CADB", "A.\tAlex had never been happy with his Indian origins.\n" +
                " \tB.\tHe set about rectifying this grave injustice by making his house in his own image of a country manor\n" +
                "C.\tFate had been unfair to him; if he had his wish, he would have been a court or an Earl on some English estate, or a medieval monarch in a chateau in France.\n" +
                "D.\tThis illusion of misplaced grandeur, his wife felt, would be Alex undoing.\n  ACBD", "\tA.\tSuch a national policy will surely divide and never unite the people.\n" +
                " \tB.\tIn fact, it suits the purpose of the politicians; they can drag the people into submission by appealing to them in the name of religion.\n" +
                "C.\tIn order to inculcate the unquestioning belief they condemn the other states, which do not follow their religion.\n" +
                "D.\tThe emergence of the theocratic states where all types of crimes are committed in the name of religion, has revived the religion of the Middle Ages.\n  DBCA", "A.\tAn essay which appeals chiefly to the intellect is Francis Bacon’s Of Studies.\n" +
                " \tB.\tHis careful tripartite division of studies expressed succinctly in aphoristic prose demands the complete attention of the mind of the reader.\n" +
                "C.\tHe considers studies as they should be; for pleasure, for self-improvement, for business.\n" +
                "D.\tHe considers the evils of excess study: laziness, affectation, and preciosity. ABCD", "\tA.\tThe establishment of the Third Reich influenced events in American history by starting a chain of events which culminated in war between Germany and the United States.\n" +
                " \tB.\tThe Neutrality Acts of 1935 and 1936 prohibited trade with an belligerents or loans to them.\n" +
                "C.\tWhile speaking out against Hitler’s atrocities, the American people generally favored isolationist policies and neutrality.\n" +
                "D.\tThe complete destruction of democracy, the persecution of jews, the war on religion, the cruelty and barbarism of the allies, caused great indignation in this country and brought on fear of another World War.\n\n  ADCB", "\tThe two boars standing silently next to the large tree…………………….not seen by the two hunters.\n" +
                " \t(a) was\t(b) were\n" +
                "(c) are\t(d) have been \n\n \twere", "I………………………no idea until he told me.\n" +
                " \t(a) was having\t(b) had\n" +
                "(c) have\t(d) was \n \n \thad", "It seemed like this buffalo ..…………never washed before I washed him this morning.\n" +
                " \t(a) was\t(b) has\n" +
                "(c) would have\t(d) had \n\n was", "I………………………if I could but I won’t because I can’t.\n" +
                " \t(a) had \t(b) shouted\n" +
                "(c) would\t(d) should \n \n \twould", "\tBangalore…..……… record rainfall in the previous years and the year before that.\n" +
                " \t(a) had had\t(b) having\n" +
                "(c) had \t(d) none \n \n \thad ", "\tA.\tTo have settled one’s affairs is a very good preparation to leading the rest of one’s life without concern for the future.\n" +
                " \tB.\tWhen I have finished this book I shall know where I stand.\n" +
                "C.\tOne does not die immediately after one has made one’s will; one makes one’s will as a precaution.\n" +
                "D.\tI can afford then to do what I choose with the years that remain to me.\n" +
                " \t(a) DBAC\t(b) CABD\t(c) BDAC\t(d) CBDA  \n \n \tCABD")
    }
}


package com.studyx.urgames.code2job

/**
 * Created by rahula on 11/03/18.
 */

class quant_ques {

    val ans: Array<String>
        get() = answers.clone()
    val ques: Array<String>
        get() = questions.clone()

    companion object {
        var questions = arrayOf("Five-digit numbers are formed using only 0, 1, 2, 3, 4 exactly once. What is the difference between the greatest and smallest numbers that can be formed  ? ", "A man has 9 friends: 4 boys and 5 girls. In how many ways can he invite them, if there have to be 3 exactly girls in the invitees ?", "A, B, C, D are four towns, any three of which are non-collinear. Then, the number of ways to construct three roads each joining a pair of towns so that the roads do not form a triangle is ?", "Three consecutive positive even numbers are such that thrice the first number exceeds double the third by 2, the third number is ?", "The remainder obtained when a prime number greater that 6 is divided by 6 is", "The number of &nbsp;common terms in the two sequences 17, 21, 25, ….. , 417 and 16, 21, 26, …,466 is ?", "Find the maximum value of the functions 1/(x2 – 3x + 2) ?", "Let g(x) =  max ( 5 – x , x + 2 ). The smallest possible value of g(x) is ?", "Let g(x) be a function such that g(x + 1) + g(x – 1) = g(x) for every real x. Then, for what value of p is the relation g(x + p)= g(x) necessarily true for every real x ? ", "The length, breadth and height of a room are in the ratio 3 : 2 : 1. If the breadth and height are halved while the length is doubled, then the total area of the four walls of the room will be decreased by ?", "A student took five papers in an examination, where the full marks were the same for each paper. His marks in these papers were in the proportion 6 : 7 : 8 : 9 : 10. In all papers together, the candidate obtained 60% of the total marks. Then the number of papers in which he got more than 50% marks is ?", "Forty percent of the employees of a certain company are men and 75% of the men earn more than Rs 25,000 per year. If 45% of the company’s employees earn more than Rs 25,000 per year, what fraction of the women employed by the company earn Rs 25,000 per year ?")
        var answers = arrayOf("Greatest five digit number : 43210<br>\n" +
                "Smallest five digit number : 10234<br>\n" +
                "Difference = 43210-10234= 32976<br>", " Girls can be selected out of 5 girls in 5C3 ways.<br>\n" +
                "Since number of boys to be invited is not given, hence out of 4 boys, he can invite them (2)<sup>4</sup>&nbsp;ways.<br>\n" +
                "Hence required number of ways is =&nbsp;5C3 x (2)<sup>4</sup>&nbsp; = 160<br>", "To construct 2 roads, three towns can be selected out of 4 in 4 x 3 x 2 = 24 ways. Now, if the third road goes from the third town to the first town, a triangle is formed and if it goes to the fourth town, a triangle is not formed. So there are 24 ways to form a triangle and 24 ways of avoiding a triangle.<br>", "Let the three even number are ( x – 2), &nbsp;x, (x + 2)<br>\n" +
                "Then, 3(x – 2) – 2(x + 2) = 2<br>\n" +
                "3x – 6 – 2x – 4 = 2 &nbsp;i.e. x=12<br>\n" +
                "Hence, the third number is (12 + 2) = 14<br>", "We have take some prime number greater than 6 i.e. 7, 11, 13, 17, 19, 23, 29,31, 37, 41<br>\n" + "Now we have divide the numbers by 6. The remainder is always either 1 or 5.<br>", "Both the sequences ( 17, 21, 25 ……………… and (16, 21, 26….. are arithmetic progression with a common difference of 4 and 5 respectively.<br>\n" +
                "In both the sequence first common term is 21.<br>\n" +
                "Hence a new arithmetic sequence containing the common terms of both the series can be formed with a common difference of LCM of ( 4, 5) is 20<br>\n" +
                "New sequence will be 21, 41, 61, ….401<br>\n" +
                "n<sup>th&nbsp;</sup>term = a + (n-1)d<br>\n" +
                "401= 21 + (n-1)20<br>\n" +
                "n-1=19<br>\n" +
                "Hence, n=20<br>", " The denominator x2 -3x + 2 has real roots. Hence the maximum value of the function f(x) will be infinity.\n", "We have to draw graph and the find the point of intersection.<br>\n" +
                "y= 5 – x<br>\n" +
                "y= x + 2<br>\n" +
                "Hence at the point of intersection of two straight line.<br>\n" +
                "Smallest of g(x)=3.5<br>", " g(x+1) + g(x-1)=g(x)<br>\n" +
                "g(x+2)+g(x)=g(x+1)<br>\n" +
                "Adding these two equation, we get<br>\n" +
                "g(x+2)+g(x-1)=0<br>\n" +
                "g(x + 3) + g(x)=0 —- (1)<br>\n" +
                " g(x + 4) + g(x + 1)=0<br>\n" +
                " g(x + 5) + g(x + 2)=0<br>\n" +
                " g(x + 6) + g(x + 3)=0<br>\n" +
                " g(x+6)-g(x)=0 ", "Let the length, breadth and height of the room be 3,2 and 1 unit respectively.<br>\n" +
                "Area of the four walls of the room = 2(l + b) h<br>\n" +
                "=2 (3 + 2) x 1= 10 sq unit<br>\n" +
                "New length, breadth and height of the room will be 6, 1 and 1/2 unit respectively.<br>\n" +
                "Hence, new area of the four walls of the room = 2 (6+1) x 0.5= 7 sq unit.<br>\n" +
                "Percentage decrease (%) = 10-7/10=30", "Let the marks scored in five subjects be 6a, 7a, 8a, 9a and 10a.<br>\n" +
                "Total marks in all the five subjects= 40a<br>\n" +
                "Max marks of the five subjects= 40a/0.6<br>\n" +
                "(40a is 60% of total marks)<br>\n" +
                "Hence, Max marks in each subject = 40a/(0.6 x 5)= 13.33a<br>\n" +
                "Hence Percentage in each subject (%)=<br>\n" +
                "*multiply each value by 100.<br>\n" +
                "6a/13.33a= 45.01%<br>\n" +
                "7a/13.33a= 52.51%<br>\n" +
                "8a/13.33a= 60.01%<br>\n" +
                "9a/13.33a=67.51%<br>\n" +
                "10a/13.33a=75.01%<br>\n" +
                "Number of papers in which he got more than 50% marks is 4.<br>", "Let number of men and women be 40 and 60 respectively.<br>\n" +
                "» Number of men earning more than Rs 25,000 = 30<br>\n" +
                "»Total number of employees earning more then Rs 25,000 = 45<br>\n" +
                "»Number of women earning more than Rs 25,000= (45 – 30)= Rs 15<br>\n" +
                "»Now, fraction of the women earning Rs 25,000 or less<br>\n" +
                "= (60 – 15)/60 = 45/60 = 3/4")
    }
}


package com.studyx.urgames.code2job;

/**
 * Created by rahula on 15/01/18.
 */

public class ques {

    public static String[] questions=  {" What is a percentile? How is it different from percentage ?","Can I take the pH test more than once?","What is the cutoff percentile to get interview calls?","What is the academic criteria for taking the pH test?",
            "How do I get eLitmus Job updates ?","Can I cancel my pH test once I have made the payment?","Can I reschedule the pH test? If so, how?","When and how will I receive my pH test scores?","I had paid for a pH test but was unable to attend...","What are the documents I need to carry to the pH test center ?"," I have arrears / backlog in the last semester. Can I take pH test?"};
    public static  String[] answers={"Lot of candidates get confused between percentile to percentage. They are vastly different and a brief explanation is below.\n" +
            "\n" +
            "Let us assume, Ram has scored 80th percentile in the pH test. It means 80% of pH test takers have scored less than him and 20% people more than him. Say there are 100000 test takers in an year. So 20% of candidates that is 20,000 in all are above Ram.","There is no restriction/time gap on the number of times you can take the pH test. Your three latest scores will be visible to the companies. Typically the best of them will be considered for shortlisting. Every time you take up the test the required fee amount has to be paid.",
            "Companies typically shortlist candidates for interviews on the basis of the skill sets required for the given job profile.\n" +
                    "\n" +
                    "For every job, the eLitmus AI engine assigns a rank to each student based on the preferences of and the job profile set by companies. So it is possible that you might be ranked higher than your friend in company “A” but in company “B” your friend is ranked higher despite having a lower score.\n" +
                    "This could be because Company A is engaged in R&D and hence gives higher weightage to individuals who have solved questions related to the following skills – out of box thinking, algorithmic thinking, simplification, relational thinking, encapsulation and abstraction. Company B might be engaged in providing software services work or QA work. And hence will give higher weightage to individuals who have solved questions related to the following skills – methodical approach, eye for detail, perseverance, patience etc.\n","We do not have any academic percentage cut-off for taking the pH test. You need to be either a final year or pre-final year graduate/post graduate student (or) should have finished your graduation/post graduation.",
            "To get E-Litmus job updates, you should join e-litmus mailing groups.\n" +
                    "The eLitmus mailing groups are hosted on GoogleGroups. There are multiple groups and you may join any of these based on your preference.\n" +
                    "To join a group, please visit www.elitmus.com/subscribe_groups \n" +
                    "\n" +
                    "Note:\n" +
                    "Google will send a mail to confirm if you want to join the group, ensure that you click on the \"confirmation link\" in the mail in order to become a member(if you do not get the mail, please check spam/bulk folder for the same)","No. Once we have received the payment, our system would automatically allocate a seat for you in the test location. Cancellation of the same is not possible, however you could reschedule your pH test to a future date and/or an alternate location for a maximum of three times and within 3 months from the date of your first pH test or DD received date, whichever is later.",

            "Yes. You can reschedule your test to a different available date and/or location, before the Last Date to Reschedule. You cannot reschedule a test after this date. \n" +
                    "\n" +
                    "To reschedule, log in to your account and Go to Tests-> My Tests and click on the link -> Reschedule.\n" +
                    "You can either\n" +
                    "a) Change the Test Location:(depending on the availability at the new location and before the last date to reschedule)\n" +
                    "b) Date of test: (depending on the availability on the new date and before the last date to reschedule)\n" +
                    "\n" +
                    "At the maximum, three reschedules are permissible","pH scores are declared in your eLitmus account within 10 working days of your pH test date.\n" +
            "\n" +
            "You can go to Tests -->Scores section to view the score." ,
            "In case you were unable to attend the pH test despite having received an admit card, you would be eligible to get a discount code of Rs.500.\n" +
                    "The code will be enabled in your eLitmus account within a week of the pH test for which you have applied.\n" +
                    "You can use the discount code to pay a part of the test fee for the next pH test.\n" +
                    "To use the code, login to your elitmus account and apply for another pH test within the next two test dates. Go to Tests ->My Tests and click on the link “Apply a scholarship code” and enter the code in the space provided. This will redeem that voucher and you will be able to take up the exam at Rs. 250. You can pay the balance amount by Cash/DD/Online.","You will have to bring the following documents in ORIGINAL FORM to the pH test center:\n" +
            "\n" +
            "1) Xth, XIIth, Graduation/Post graduation marksheets (Final year students need to carry the marks sheets available till date).\n" +
            "2) Government issued Photo identity card (like Driving license, passport, Voter Id card etc).\n" +
            "3) eLitmus Admit Card with photo pasted.\n" +
            "\n" +
            "Your pH Test score will be withheld if the above credentials are not verified at the time of the test.\n" +
            "\n" +
            "Additionally, any mismatch in the details provided by you at the time of registration with that during verification can be corrected by filling the discrepancy column in the admit card at the test center.\n" +
            "\n" +
            "You would marked as absent & hence your score could be withheld for one of the following reasons:\n" +
            "1) Admit card not produced and/or\n" +
            "2) Admit card without your photograph and/or\n" +
            "3) No Photo ID proof and/or\n" +
            "4) X, XII, Graduation original marks cards not verified.\n" +
            "5) Our system has flagged you for possible violation of certain terms and conditions.\n" +
            "\n" +
            "You can bring your original documents to our Bangalore office and get it verified between 10.00 AM to 5.00 PM\n" +
            "For verification of original marks cards, in centers other than Bangalore, you have to bring a printout of your admit card along with original marks cards at the next pH test date and meet the eLitmus supervisor.\n" +
            "Please call us on our support number to get the test venue 2 days before the test date.\n" +
            "Once your original documents are verified your score would be released within 2 weeks.",
            "Though there is no eligibility criteria for writing the pH test, companies may have their own criteria pertaining to backlogs. We would therefore, suggest you to clear your backlogs before taking the pH test or at least before applying for companies."


    };
    public String[] getAns()
    {
        return answers.clone();
    }
    public String[] getQues()
    {
        return questions.clone();
    }
}

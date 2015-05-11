#Class Quiz App
####An Android app about to do list. You can add, edit and delete a list.

Instructions for proClassicsQuiz


Setup:
1/ Follow instructions on video: http://android.cs.uchicago.edu/content/videos/projects_in_studio.webm
2/ Make sure that both davidselvaraj and csgerber have WRITE access to your remote repo. Make sure that it is private. 
3/ Please note that there is no base code. You are starting from scratch. 


hierarchy.png:
You will create an app called proClassicsQuiz. You will use standard activities in this app -- there is no need for fragments. The 'Latin', 'Greek', and 'Mixed' Buttons will generate a quiz with an indefinite number of questions. The quiz is only over when the user quits (max-out at Integer.MAX_VALUE), however the app must keep track of the number of questions answered correctly/incorrectly.   You should define a string array called word_classics with foreign_word, english_word, and language sepeated by pipes like below. Gather your word_classics from these wikis or elsewhere on the web. Use at least 20 from each language and make sure a few of them have the same meaning in english, such as equus and hippos, and bis and dis, etc. 

http://en.wikipedia.org/wiki/List_of_Latin_words_with_English_derivatives
http://en.wikipedia.org/wiki/List_of_Greek_words_with_English_derivatives


<resources>
    <string-array
        name="word_classics">
        <item>civis|citizen|LAT</item>
	<item>dignus|worthy|LAT</item>
	<item>equus|horse|LAT</item>
		...
	<item>thermos|hot|GRK</item>
	<item>hippos|horse|GRK</item>
		...
    </string-array>
</resources>


To see more about String Arrays, see:
http://developer.android.com/guide/topics/resources/string-resource.html#StringArray


Home.png:
The quiz starts when the user clicks Latin, Greek, or Mixed. When the user clicks the Latin or Greek buttons, then when you generate a question randomly drawn from the entire pool, the correct answer as well as the wrong answers should be drawn from the same language. If the user clicks Mixed, then the the questions and answers are mixed. 


question.png:
Make sure you display the question serial number on top. In the second mixed question example below, dis is twice in greek, and bis is twice is latin. Be careful not to serve-up a trick question in which there are two correct answers. Make sure your getQuestion() algorithm checks for this condition and does NOT serve trick questions. 


For example: 
Latin only:

Which is tree?
o siccus
o arbor
o lac
o vulnus
o appello


Greek only:
Which is riddle?
o ganos
o emphasis
o theos
o ainigma
o kosmos

Mixed 
Which is twice?
o vox
o circum
o demnion
o dis
o hippos


Mixed (trick-question)
Which is twice?
o dis
o vox
o circum
o bis
o hippos


results.png
The results page is formatted as show with green, red, and white text. Reset take the user back to the Home page and allows him/her to reset her name, while 'another quiz' retains the user's name, but sets the question-count, correct-count, and incorrect-count to 1,0,0 respectively. 


As you examine the base-code of proClassicsQuiz, pay attention to the following:

Like with labGeoQuiz2g, the Question class should probably implement the Serializable interface. Serializable is the only interface in Java that has NO contract methods. When you pass a regular (unserialized) object, you're just passing a copy of the memory address of that object. The problem with doing that in Android is that due to its modularity, you're not gauranteed that the target component (Activity, Service, BroadcastReceiver) lives in the same memory space.  So, Android forces you to serialize an object when passing it among components, even activities within the same app! Primitives can be passed as they are -- and that makes sense since in Java primitives are passed by value and no memory locations need to be referenced.  

Here are some examples:


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //pass the question into the bundle when I have a config change
        outState.putSerializable(QuestionActivity.QUESTION, mQuestion);
    }




    //overloaded to take savedInstanceState
    private void fireQuestion(Bundle savedInstanceState){

        if (savedInstanceState == null ){
            mQuestion = getQuestion();
        } else {
            mQuestion = (Question) savedInstanceState.getSerializable(QuestionActivity.QUESTION);
        }

        populateUserInterface();

    }

In above code, notice how I'm overriding the onSaveInstanceState() method and passing in the mQuestion object to the bundle. The onSaveInstanceState() method will be called in the event of a config-change (when you rotate your phone) or if the op-system decides to bounce your activity because it needs more resources. Once the QuestionActivity is resurrected, then that same bundle will be passed back into the onCreate() method, which will inflate the appropriate layout and then delegate the task of checking to see if the savedInstanceState bundle is null to fireQuestion(Bundle savedInstanceState). If the savedInstanceState bundle is null, it means that the QuestionActivity was called from another activity and it should generate a new question. However, if that savedInstanceState bundle is not null, then it means that QuestionActivity was bounced (destroyed and resurrected) most likely due to the user rotating his/her phone. If a bounce did occur, that savedInstanceState bundle will be loaded with the current question. The getSerializable() method of Bundle will deserialize the object and return a Serializable, and so you have to cast it to a Question. Using bundles is a great way to maintain state across activity calls, and it is ideally suited to bounces. 

Using singletons is another equally effective way to maintain state within the same app. Notice how QuizTracker.java (similar to labGeoQuiz2g) follows the singleton pattern with lazy initialization. Activity members are often flushed -- for example in the event of a bounce, so notice that QuizTracker is nowhere a member of any Activity. 




 



package info.yiqin.proclassicsquiz;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yiqin on 4/8/15.
 */
public class Question implements Serializable {

    private static final long serialVersionUID = 6546546516546843135L;


    private String mCountry;
    private String mCapital;
    private String mRegion;
    private Set<String> mWrongAnswers = new HashSet<String>();

    public Question(String country, String capital, String region) {
        this.mCountry = country;
        this.mCapital = capital;
        this.mRegion = region;
    }

    public String getRegion() {
        return mRegion;
    }

    public String getCapital() {
        return mCapital;
    }

    public String getCountry() {
        return mCountry;
    }

    public Set<String> getWrongAnswers() {
        return mWrongAnswers;
    }

    public boolean addWrongAnswer(String wrongAnswer){
        return mWrongAnswers.add(wrongAnswer);
    }

    public String getQuestionText(){
        return "What is the mCapital of " + mCountry + "?";
    }





}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.util.ArrayList;

/**
 * A container for the participants results.
 * @author Asbamz
 */
public class Result 
{
    private String name;
    private ArrayList<String> answers;
    private String score;
    
    /**
     * Add the information to the Object.
     * @param name Name of Participant
     * @param answers Array of answers ["Agree", "Neutral", "Neutral", "Disagree"].
     * @param score Score of the answers.
     */
    public Result(String name, ArrayList<String> answers, String score)
    {
        this.name = name;
        this.answers = answers;
        this.score = score;
    }
    
    /**
     * Get the name of participant.
     * @return name of participant.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Change name of participant.
     * @param name name of participant.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Get the array of answers.
     * @return array of answers.
     */
    public ArrayList<String> getAnswers()
    {
        return answers;
    }
    
    /**
     * Change array of answers.
     * @param answers array of answers.
     */
    public void setAnswers(ArrayList<String> answers)
    {
        this.answers = answers;
    }
    
    /**
     * Get the score.
     * @return score.
     */
    public String getScore()
    {
        return score;
    }
    
    /**
     * Change score.
     * @param score Score.
     */
    public void setScore(String score)
    {
        this.score = score;
    }
}

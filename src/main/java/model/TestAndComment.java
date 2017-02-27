package model;

/**
 * Created by acer on 16.02.2017.
 */
public class TestAndComment {
    private int test;
    private Comment comment;
    private String input;
    private String output;

    public TestAndComment(int test, Comment comment) {
        this.test = test;
        this.comment = comment;
    }

    public TestAndComment(int test, Comment comment, String input, String output) {
        this.test = test;
        this.comment = comment;
        this.input = input;
        this.output = output;
    }

    public int getTest() {
        return test;
    }

    public Comment getComment() {
        return comment;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}

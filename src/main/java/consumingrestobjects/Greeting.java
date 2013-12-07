package consumingrestobjects;

/**
 * @author Rafal Zawadzki
 */
public class Greeting {

    private long id = 0;
    private String content = "";

    public Greeting() {
    }

    public Greeting(String content) {
            this.id = 999;
            this.content = content;
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
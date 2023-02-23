package model.entity;

/**
 * 留言
 */
public class LeavingMessage {

    private Long id;

    //用户id
    private Long uid;

    private String reply;

    //内容
    private String content;

    private String createAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "LeavingMessage{" +
                "id:" + id +
                ", uid:" + uid +
                ", reply:'" + reply + '\'' +
                ", content:'" + content + '\'' +
                ", createAt:'" + createAt + '\'' +
                '}';
    }
}

package com.jcpl.persist;

/**
 * @author Administrator
 */
public class OperateLog {
    private long id;
    private String username;
    private int op;
    private String detail;
    private int res;
    private String createTime;

    public long getId() {
        return id;
    }

    public OperateLog setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OperateLog setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getOp() {
        return op;
    }

    public OperateLog setOp(int op) {
        this.op = op;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public OperateLog setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public int getRes() {
        return res;
    }

    public OperateLog setRes(int res) {
        this.res = res;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public OperateLog setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "OperateLog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", op=" + op +
                ", detail='" + detail + '\'' +
                ", res=" + res +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public enum Op {
        /**
         * 发送求助信息类型操作
         */
        SEND_MSG(0, "发送求助信息");
        private int index;
        private String description;

        Op(int index, String description) {
            this.index = index;
            this.description = description;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

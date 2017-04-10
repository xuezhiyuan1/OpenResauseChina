package jiyun.com.openresausechina.model;

import java.util.List;

/**
 * Created by think on 2017/4/6.
 */


/*@org.simpleframework.xml.Root(name = "oschina")*/
public class SoftNetBean {
    @Override
    public String toString() {
        return "SoftNetBean{" +
                "pagesize='" + pagesize + '\'' +
                ", results=" + results +
                '}';
    }

    private String pagesize;
    private List<ResultBean> results;

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public List<ResultBean> getResults() {
        return results;
    }

    public void setResults(List<ResultBean> results) {
        this.results = results;
    }

    //@org.simpleframework.xml.Root(name = "result")
    public static class ResultBean {
        private String objid;
        private String type;
        private String title;
        private String description;
        private String url;
        private String pubDate;
        private String author;

        public String getObjid() {
            return objid;
        }

        public void setObjid(String objid) {
            this.objid = objid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}

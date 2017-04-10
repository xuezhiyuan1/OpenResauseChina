package jiyun.com.openresausechina.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by think on 2017/4/10.
 */
@Entity
public class AutoLogin {
    @Id(autoincrement = true)
        private Long id;
        private String name;
        private String portrait;
        private String jointime;
        private String gender;
        private String score;
        private String from;
        private String devplatform;
        private String expertise;
        private String favoritecount;
        private String fans;
        private String followers;
        @Generated(hash = 516795992)
        public AutoLogin(Long id, String name, String portrait, String jointime,
                String gender, String score, String from, String devplatform,
                String expertise, String favoritecount, String fans, String followers) {
            this.id = id;
            this.name = name;
            this.portrait = portrait;
            this.jointime = jointime;
            this.gender = gender;
            this.score = score;
            this.from = from;
            this.devplatform = devplatform;
            this.expertise = expertise;
            this.favoritecount = favoritecount;
            this.fans = fans;
            this.followers = followers;
        }
        @Generated(hash = 273935440)
        public AutoLogin() {
        }
        public Long getId() {
            return this.id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return this.name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPortrait() {
            return this.portrait;
        }
        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
        public String getJointime() {
            return this.jointime;
        }
        public void setJointime(String jointime) {
            this.jointime = jointime;
        }
        public String getGender() {
            return this.gender;
        }
        public void setGender(String gender) {
            this.gender = gender;
        }
        public String getScore() {
            return this.score;
        }
        public void setScore(String score) {
            this.score = score;
        }
        public String getFrom() {
            return this.from;
        }
        public void setFrom(String from) {
            this.from = from;
        }
        public String getDevplatform() {
            return this.devplatform;
        }
        public void setDevplatform(String devplatform) {
            this.devplatform = devplatform;
        }
        public String getExpertise() {
            return this.expertise;
        }
        public void setExpertise(String expertise) {
            this.expertise = expertise;
        }
        public String getFavoritecount() {
            return this.favoritecount;
        }
        public void setFavoritecount(String favoritecount) {
            this.favoritecount = favoritecount;
        }
        public String getFans() {
            return this.fans;
        }
        public void setFans(String fans) {
            this.fans = fans;
        }
        public String getFollowers() {
            return this.followers;
        }
        public void setFollowers(String followers) {
            this.followers = followers;
        }


}

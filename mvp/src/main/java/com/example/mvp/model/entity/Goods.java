package com.example.mvp.model.entity;

import java.util.List;


public class Goods {


    /**
     * data : [{"goods_id":"1","goods_sn":"ECS000000","goods_name":"KD876","goods_thumb":"images/200905/thumb_img/1_thumb_G_1240902890710.jpg","goods_img":"images/200905/goods_img/1_G_1240902890755.jpg","market_price":"1665.60","shop_price":"1388.00"},{"goods_id":"4","goods_sn":"ECS000004","goods_name":"诺基亚N85原装充电器","goods_thumb":"images/200905/thumb_img/4_thumb_G_1241422402467.jpg","goods_img":"images/200905/goods_img/4_G_1241422402722.jpg","market_price":"69.60","shop_price":"58.00"},{"goods_id":"3","goods_sn":"ECS000002","goods_name":"诺基亚原装5800耳机","goods_thumb":"images/200905/thumb_img/3_thumb_G_1241422082679.jpg","goods_img":"images/200905/goods_img/3_G_1241422082168.jpg","market_price":"81.60","shop_price":"68.00"},{"goods_id":"5","goods_sn":"ECS000005","goods_name":"索爱原装M2卡读卡器","goods_thumb":"images/200905/thumb_img/5_thumb_G_1241422518886.jpg","goods_img":"images/200905/goods_img/5_G_1241422518773.jpg","market_price":"24.00","shop_price":"20.00"},{"goods_id":"6","goods_sn":"ECS000006","goods_name":"胜创KINGMAX内存卡","goods_thumb":"","goods_img":"","market_price":"50.40","shop_price":"42.00"},{"goods_id":"7","goods_sn":"ECS000007","goods_name":"诺基亚N85原装立体声耳机HS-82","goods_thumb":"images/200905/thumb_img/7_thumb_G_1241422785492.jpg","goods_img":"images/200905/goods_img/7_G_1241422785856.jpg","market_price":"120.00","shop_price":"100.00"},{"goods_id":"8","goods_sn":"ECS000008","goods_name":"飞利浦9@9v","goods_thumb":"images/200905/thumb_img/8_thumb_G_1241425513488.jpg","goods_img":"images/200905/goods_img/8_G_1241425513055.jpg","market_price":"478.79","shop_price":"399.00"},{"goods_id":"9","goods_sn":"ECS000009","goods_name":"诺基亚E66","goods_thumb":"images/200905/thumb_img/9_thumb_G_1241511871555.jpg","goods_img":"images/200905/goods_img/9_G_1241511871574.jpg","market_price":"2757.60","shop_price":"2298.00"},{"goods_id":"10","goods_sn":"ECS000010","goods_name":"索爱C702c","goods_thumb":"images/200905/thumb_img/10_thumb_G_1242973436403.jpg","goods_img":"images/200905/goods_img/10_G_1242973436141.jpg","market_price":"1593.60","shop_price":"1328.00"},{"goods_id":"11","goods_sn":"ECS000011","goods_name":"索爱C702c","goods_thumb":"","goods_img":"","market_price":"0.00","shop_price":"1300.00"}]
     * res : 1
     */

    private int res;
    /**
     * goods_id : 1
     * goods_sn : ECS000000
     * goods_name : KD876
     * goods_thumb : images/200905/thumb_img/1_thumb_G_1240902890710.jpg
     * goods_img : images/200905/goods_img/1_G_1240902890755.jpg
     * market_price : 1665.60
     * shop_price : 1388.00
     */

    private List<DataBean> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String goods_id;
        private String goods_sn;
        private String goods_name;
        private String goods_thumb;
        private String goods_img;
        private String market_price;
        private String shop_price;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }
    }
}

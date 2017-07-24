package com.zm.framegather.util;

/**
 * @author 张伟明
 * @ClassName: Constants
 * @Description: 全局常量
 * @date 2016年10月13日
 */
public class Constants {

    public static final String webServer = "http://115.28.178.117:9021/redEnvelopeFun";
    public static final String picServer = "http://115.28.178.117:9021/redEnvelopeFunWeb/";

    /*
     *  网络常量
     */
    public static class Net {
        // 登录
        public static final String Login = webServer + "/front/userAct.htm?operate=login";

        // 微信登录
        public static final String wxlogin = webServer + "front/userAct.htm?&operate=loginWx";

        // 找回密码
        public static final String FindPwd = webServer + "/front/userAct.htm?operate=findPwd";

        // 上传头像
        public static final String Upload = webServer + "/front/uploadAct.htm";

        // 注册
        public static final String Reg = webServer + "/front/userAct.htm?operate=reg";

        // 短信验证
        public static final String Verification = webServer + "/front/sendTemplateSmsAct.htm";

        // 修改密码
        public static final String UpdatePwd = webServer + "/front/userAct.htm?operate=updatePwd";

        // 显示用户信息
        public static final String FindUser = webServer + "/front/userAct.htm?operate=findUser";

        // 修改用户信息
        public static final String UpdatUser = webServer + "/front/userAct.htm?operate=updatUser";

        // 环信用户（群匹配）根据环信ID得到服务器对应的ID
        public static final String UserDel = webServer + "/front/userAct.htm?operate=userDel";

        // 查看/屏蔽聊天消息状态，传userId 如果要改屏蔽状态传isScreen 0设为不屏蔽， 1屏蔽
        public static final String FindScreen = webServer + "/front/userAct.htm?operate=findScreen";

        // 查看是否聊天过传userId  otherId
        public static final String IsFriends = webServer + "/front/userAct.htm?operate=isFriends";

        // 平台简介，帮助信息，用户协议（网页显示）
        public static final String ShowAbout = webServer + "/front/helpAct.htm?operate=showAbout";

        // 意见反馈
        public static final String AddFeedBack = webServer + "/front/helpAct.htm?operate=addFeedBack";

        // 版本信息
        public static final String ShowParams = webServer + "/front/helpAct.htm?operate=showParams";

        // 查看可加入房间列表
        public static final String ShowGroupInfo = webServer + "/front/groupInfoAct.htm?operate=showGroupInfo";

        // 申请加入房间
        public static final String ApplyGroupInfo = webServer + "/front/groupInfoAct.htm?operate=applyGroupInfo";

        // 查看已加入房间列表
        public static final String ShowMyGroupInfo = webServer + "/front/groupInfoAct.htm?operate=showMyGroupInfo";

        // 查看房间成员
        public static final String GroupUser = webServer + "/front/groupInfoAct.htm?operate=groupUser";

        // 修改群成员状态（拉人，踢人，退群）
        public static final String UpdateStatus = webServer + "/front/groupInfoAct.htm?operate=updateStatus";

        // 房间广告图
        public static final String ShowGroupImg = webServer + "/front/groupInfoAct.htm?operate=showGroupImg";

        // 转账
        public static final String AddTransaction = webServer + "/front/transactionAct.htm?operate=addTransaction";

        // 确认转账
        public static final String ConfirmTransaction = webServer + "/front/transactionAct.htm?operate=confirmTransaction";

        // 转账记录
        public static final String ShowTransaction = webServer + "/front/transactionAct.htm?operate=showTransaction";

        // 查看通知列表
        public static final String ShowUserMessage = webServer + "/front/userMessageAct.htm?operate=showUserMessage";

        // 删除通知消息
        public static final String DeleteUserMessage = webServer + "/front/userMessageAct.htm?operate=deleteUserMessage";

        // 同意加入
        public static final String AgreeGroup = webServer + "/front/userMessageAct.htm?operate=agreeGroup";

        // 发送红包
        public static final String AddRedEnvelope = webServer + "/front/redEnvelopeInfoAct.htm?operate=addRedEnvelope";

        // 查看用户剩余积分
        public static final String ShowPoint = webServer + "/front/redEnvelopeInfoAct.htm?operate=showPoint";

        // 领取红包
        public static final String DrawRedEnvelope = webServer + "/front/redEnvelopeInfoAct.htm?operate=drawRedEnvelope";

        // 查看所有红包
        public static final String AllGroupInfo = webServer + "/front/redEnvelopeInfoAct.htm?operate=allGroupInfo";

        // 查看具体红包
        public static final String ShowRedPackets = webServer + "/front/redEnvelopeInfoAct.htm?operate=showGroupInfo";

        // 再次发包
        public static final String AgainSend = webServer + "/front/redEnvelopeInfoAct.htm?operate=againSend";

        // 聊天
        public static final String AddPrivateMessage = webServer + "/front/privateMessageAct.htm?operate=addPrivateMessage";

        // 私聊列表
        public static final String ShowPrivateUser = webServer + "/front/privateMessageAct.htm?operate=showPrivateUser";

        // 积分商城
        public static final String ShowCommodityInfo = webServer + "/front/commodityInfoAct.htm?operate=showCommodityInfo";

        // 我的兑换
        public static final String MyCommodityInfo = webServer + "/front/commodityInfoAct.htm?operate=myCommodityInfo";

        // 查看积分状态
        public static final String SelectReceive = webServer + "/front/transactionAct.htm?operate=selectReceive";


    }

    public static final String PAGE_SIZE = "20";

    public static String CHANGE_GROUP_ID = "";

    public static String[] urls = {"http://img3.redocn.com/tupian/20150806/weimeisheyingtupian_4779232.jpg",
            "http://image72.360doc.com/DownloadImg/2014/05/0402/41292510_3.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=79520e92632762d0803ea4b790ed0849/8a6104a4462309f740ec1ca3720e0cf3d6cad6a8.jpg",
            "http://img1.3lian.com/2015/w7/85/d/101.jpg",
            "http://img01.taopic.com/141002/240423-14100210124112.jpg",
            "http://image72.360doc.com/DownloadImg/2014/05/0402/41292510_3.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=79520e92632762d0803ea4b790ed0849/8a6104a4462309f740ec1ca3720e0cf3d6cad6a8.jpg",
            "http://img1.3lian.com/2015/w7/85/d/101.jpg",
            "http://pic95.nipic.com/file/20160406/20616631_131705638000_2.jpg",
            "http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJlbKxmyIJBaWAAeJtiCgphUAALHlAEaoHgAB4nO760.jpg",
            "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1209/12/c3/13816475_1347443741996_800x800.jpg",
            "http://image101.360doc.com/DownloadImg/2016/10/2719/83195451_8.jpg",
            "http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/01/0E/ChMkJlbKwhKIPf_RAAweZKvhDqMAALGiQLPZ9QADB58872.jpg",
            "http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/04/ChMkJ1bKx4qIGTZLAAmjEID8lj0AALH1ADgWvsACaMo983.jpg",
            "http://www.3lian.com/d/file/201612/14/e76c2cc362e5f414e7a6a7cd4a66c6d8.jpg",
            "http://pic1.win4000.com/mobile/d/581c3bd22e898.jpg",
            "http://www.3lian.com/d/file/201612/14/cdb69206a570df36bfe58d729de01555.jpg",
            "http://tupian.enterdesk.com/2013/mxy/12/10/15/3.jpg",
            "http://www.3lian.com/d/file/201612/14/f882ccbc825a22b9b20e9af3f07aec0d.jpg",
            "http://img01.taopic.com/141002/240423-14100210124112.jpg"};

    /**
     * 第三方登录
     */
    public static class otherKey {
        public static final String baiduKey = "TMIz2Wb4iVdPcR8E0IN2WohC";
        public static final String sinaKey = "3419987330";
        public static final String qqKey = "1104677069";
        public static final String winxinKey = "wx28b436cb062d55f2";
        public static final String weiboScret = "00a51f343bd22abbf4bb4ee887e0de16";
        public static final String weiboScope = "email,direct_messages_read,direct_messages_write,"
                + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                + "follow_app_official_microblog," + "invitation_write";
        public static final String weiboURL = "https://api.weibo.com/oauth2/default.html";
    }

    public static class WeiXin {
        // 请同时修改 androidmanifest.xml里面，添加调用微信支付的activity里的属性<data
        // android:scheme="wxe9546a8b8614b7c6"/>
        // appid
        public static final String APP_ID = "wx28b436cb062d55f2";
        public static final String AppSecret = "cbf08e0127480a48998a178ec3acb207";
        // 商户号
        public static final String MCH_ID = "1259444901";
        // API密钥，在商户平台设置
        public static final String API_KEY = "chinalanyaookeji1234567891234561";
        // 接收微信支付异步通知回调地址
        public static final String notify_url = webServer
                + "/front/microPaylAction.htm";
        // 微信授权域
        public static final String scope = "snsapi_userinfo";
//        public static BaseResp resp;// 登录信息
//        public static BaseResp PayResp;// 支付信息
    }

    // 3.支付宝操作相关的常量
    public static final class AlipayStatus {
        public static final int PAY_SUCCESS = 9000; // 订单支付成功
        public static final int PAY_PROCESSING = 8000; // 订单正在处理中
        public static final int PAY_FAILED = 4000; // 订单支付失败
        public static final int PAY_CANCELLED = 6001; // 用户中途取消
        public static final int PAY_NET_ERROR = 6002; // 网络连接出错
        public static final String notify_url = webServer + "/front/notifyUrlAct.htm";//订单支付
        public static final String notify_url_recharge = webServer + "/front/rechargeAct.htm";//充值
    }

    /**
     * intent 返回值
     */
    public static class ResultCode {
        public static final int shopCart = 0x1111;
        public static final int Topay = 0x1112;
        public static final int fail = 0x1113;
        public static final int AddAddress = 0x1114;
        public static final int userAddress = 0x1115;
    }

    public static final class Cache {
        public static final String CACHE_PIC_DIRECTORY = "zhimei/thumbs";
        public static final String APP_UPDATE = "zhimei/app";
        public static boolean ISREFRESH = true;

    }

    /**
     * sharepreference key
     */
    public static class Preference {
        // 获取门店信息
        public static final String CollegeName = "CollegeName";
        // 获取从什么地方跳转登录
        public static final String LoginType = "LoginType";

        //------------------------------------------------
        //用户信息
        public static final String User_Info = "UserInfo";
    }
}

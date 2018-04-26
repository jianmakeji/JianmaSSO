/**
 * Created with JetBrains WebStorm.
 * User: ty
 * Date: 14-10-5
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
var config={
    uploader:{
        url:"file/uploadMultiFile",
        swfUrl:"resources/js/lib/Moxie.swf",
        sizes:{
            all:"5120m",
            img:"2m"
        },
        filters:{
            all:"*",
            zip:"zip,rar",
            img:"jpg,JPG,jpeg,JPEG,png,PNG"
        },
        qiNiu:{
            upTokenUrl:"qi-niu/up-token",
            uploadDomain:"http://qiniu-plupload.qiniudn.com/",
            bucketDomain:"http://7xplk9.com1.z0.glb.clouddn.com/"
        },
        aLiYun:{
            getSignatureUrl:"#",
            host: "",
            policy: "",
            accessKey: "",
            signature: "",
            expire: 0
        },
        fileType:{
            others:100,
            attachFile:1,
            newsImageFile:2,
            productionFile:3
        }
    },

    ajaxUrls:{
    	loginHome:"authorityCheck",
    	
    	
        imageGet:"file/image",
        newsGetAll:"news/findNewsByPage",
        newsGetByPage:"news/findNewsByPage",
        newsCreate:"news/createNews",
        newsUpdate:"news/updateNews",
        newsRemove:"news/deleteNews/:id",
        newsDetail:"news/findNewsById/:id",
        manageNews:"news/findManageNewsByPage",
        userGetByPage:"user/getDataTableUserByPage",
        userActiveAction:"user/resetUserValid",
        worksGetByPage:"production/getDataTableProductionByPage",
        workGetById:"production/getProductionDetailById/:id",
        workRemove:"production/deleteProduction/:id",
        workSetStatus:"production/updateProductionStatus",
        workSetRound:"review/bindProductAndRound",
        workComputeScore:"production/updateProductionScore",
        judgeGetByPage:"judge/findJudgesByPage",
        judgeRemove:"judge/deleteJudge/:id",
        judgeCreate:"judge/createJudge",
        judgeUpdate:"judge/updateJudge",
        judgeDetail:"judge/findJudgeById/:id",
        judgeRoundDetail:"roundJudge/getRoundJudgeById",
        judgeRoundCreate:"roundJudge/createRoundJudge",
        judgeRoundUpdate:"roundJudge/updateRoundJudge",
        judgeRoundRemove:"roundJudge/deleteRoundJudge/:id",
        judgeRoundGetByPage:"roundJudge/getRoundJudgeByPage",
        judgeRoundSetJudge:"roundJudge/bindingRoundJudge",
        judgeRoundChange:"roundJudge/updateBindRoundJudge",//评审轮次评委的修改接口
        sendEmail:"review/sendReviewEmail",
        getRoundScoreBean:"review/getScoreByProductId"
    },
    viewUrls:{
        newsMgr:"news/newsMgr",
        newsUpdate:"news/newsCOU/:id",
        judgeMgr:"judge/judgeMgr",
        judgeUpdate:"judge/judgeCOU/:id",
        judgeRoundMgr:"roundJudge/judgeRoundMgr",
        judgeRoundUpdate:"roundJudge/judgeRoundCOU/:id",
        manageWorkDetail:"production/manageWorkDetail/:id"
    },
};


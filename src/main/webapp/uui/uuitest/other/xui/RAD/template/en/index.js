// Ĭ�ϵĴ�����һ���� xui.Com ����������
Class('{className}', 'xui.Com',{
    // Ҫȷ����ֵ�Ե�ֵ���ܰ����ⲿ����
    Instance:{
        // ʵ��������Ҫ�ڴ˺����г�ʼ������Ҫֱ�ӷ���Instance��
        initialize : function(){
            // ��Com�Ƿ����ŵ�һ���ؼ������ٶ�����
            this.autoDestroy = true;
            // ��ʼ������
            this.properties = {};
        },
        // ��ʼ���ڲ��ؼ���ͨ������༭�����ɵĴ��룬�󲿷��ǽ���ؼ���
        // *** ��������Ƿǳ���ϤXUI��ܣ��������ֹ��ı䱾�����Ĵ��� ***
        iniComponents : function(){
            // [[Code created by CrossUI RAD Tools
            var host=this, children=[], append=function(child){children.push(child.get(0));};
            
            append(
                (new xui.UI.SButton())
                .setHost(host,"ctl_sbutton1")
                .setLeft(90)
                .setTop(40)
                .setCaption(" �� �� ")
                .onClick("_ctl_sbutton1_onclick")
            );
            
            return children;
            // ]]Code created by CrossUI RAD Tools
        },
        // ��������Com�����ñ�����
        iniExComs : function(com, threadid){
        },
        // �����Զ�����Щ����ؼ����ᱻ�ӵ���������
        customAppend : function(parent, subId, left, top){
            // "return false" ��ʾĬ����������еĵ�һ���ڲ�����ؼ��ᱻ���뵽������
            return false;
        },
        // Com������¼�ӳ��
        events : {},
        // ���ӣ�button �� click �¼�����
        _ctl_sbutton1_onclick : function (profile, e, src, value) {
            var uictrl = profile.boxing();
            xui.alert("���� " + uictrl.getAlias());
        }
    }
});
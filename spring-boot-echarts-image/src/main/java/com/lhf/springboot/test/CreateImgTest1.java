package com.lhf.springboot.test;

import com.lhf.springboot.util.EchartsUtil;
import com.lhf.springboot.util.ImageUtil;
import freemarker.template.TemplateException;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

/**
 * @ClassName: CreateImgTest1
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/4/29 15:30
 */
public class CreateImgTest1 {

    public static void main(String[] args) throws ClientProtocolException, IOException, TemplateException {

        String url = "http://127.0.0.1:6668";

        String option = "{'title': {'text': '地市数据'},'toolbox': {'feature': {'mark': {'show': true,'title': {'markUndo': '删除辅助线','markClear': '清空辅助线','mark': '辅助线开关'},'lineStyle': {'color': '#1e90ff','type': 'dashed','width': 2}},'dataView': {'show': true,'title': '数据视图','readOnly': false,'lang': ['数据视图','关闭','刷新']},'magicType': {'show': true,'title': {'bar': '柱形图切换','stack': '堆积','tiled': '平铺','line': '折线图切换'},'type': ['line','bar']},'restore': {'show': true,'title': '还原'},'saveAsImage': {'show': true,'title': '保存为图片','type': 'png','lang': ['点击保存']}},'show': true},'tooltip': {'formatter': '{a}<br/>{b} : {c}','show': true},'legend': {'data': ['地市数据']},'xAxis': [{'type': 'category','data': ['广州','深圳','珠海','汕头','韶关','佛山','北京','天津','昆明','武汉']}],'yAxis': [{'type': 'value'}],'series': [{'name': '地市数据','type': 'bar','data': [{'value': 6030,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 7800,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 5200,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 3444,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 2666,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 5708,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 9000,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 8765,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 7651,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 6534,'itemStyle': {'normal': {'color': 'blue'}}}]}]}";

        // 根据option参数
        String base64 =  EchartsUtil.generateEchartsBase64(option, url);

        System.out.println("BASE64:" + base64);
        ImageUtil.generateImage(base64,  "image/test1111.png");
    }
}

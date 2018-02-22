package org.misha;

import org.apache.log4j.Logger;
import org.misha.hash.HashMaker;
import org.misha.hash.Range;
import org.misha.hash.Ranges;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * author: misha
 * date: 2/20/18
 */
public class Test {
    private static final Logger log = Logger.getLogger(Test.class);
    private static final String LI_BO = "唐诗十五首\n" +
            "李白\n" +
            "\n" +
            "静夜思\n" +
            "床前明月光，\n" +
            "疑是地上霜。\n" +
            "举头望明月，\n" +
            "低头思故乡。 \t\n" +
            "\n" +
            " \n" +
            "\n" +
            "秋浦歌\n" +
            "白发三千丈，\n" +
            "缘愁似个长。\n" +
            "不知明镜里，\n" +
            "何处得秋霜。\n" +
            "\n" +
            "送友人\n" +
            "青山横北郭，白水绕东城。\n" +
            "此地一为别，孤蓬万里征。\n" +
            "浮云游子意，落日故人情。\n" +
            "挥手自兹去，萧萧班马鸣。\n" +
            "\n" +
            "子夜四时歌：秋歌\n" +
            "长安一片月，万户捣衣声。\n" +
            "秋风吹不尽，总是玉关情。\n" +
            "何日平胡虏，良人罢远征？\n" +
            "\n" +
            "早发白帝城\n" +
            "朝辞白帝彩云间，\n" +
            "千里江陵一日还。\n" +
            "两岸猿声啼不住，\n" +
            "轻舟已过万重山。\n" +
            "\n" +
            "望庐山瀑布\n" +
            "日照香炉生紫烟，\n" +
            "遥看瀑布挂前川，\n" +
            "飞流直下三千尺，\n" +
            "疑是银河落九天。\n" +
            "\n" +
            "黄鹤楼送孟浩然之广陵\n" +
            "故人西辞黄鹤楼，\n" +
            "烟花三月下扬州。\n" +
            "孤帆远影碧空尽，\n" +
            "唯见长江天际流。\n" +
            "\n" +
            "望天门山\n" +
            "天门中断楚江开，\n" +
            "碧水东流至此回。\n" +
            "两岸青山相对出，\n" +
            "孤帆一片日边来。\n" +
            "\n" +
            "赠汪伦\n" +
            "李白乘舟将欲行，\n" +
            "忽闻岸上踏歌声。\n" +
            "桃花潭水深千尺 ，\n" +
            "不及汪伦送我情。\n" +
            "\n" +
            "春夜洛城闻笛\n" +
            "谁家玉笛暗飞声，\n" +
            "散入春风满洛城。\n" +
            "此夜曲中闻折柳，\n" +
            "何人不起故园情。\n" +
            "\n" +
            "客中行\n" +
            "兰陵美酒郁金香，\n" +
            "玉碗盛来琥珀光。\n" +
            "但使主人能醉客，\n" +
            "不知何处是他乡。\n" +
            "\n" +
            "登金陵凤凰台\n" +
            "凤凰台上凤凰游，\n" +
            "凤去楼空江自流。\n" +
            "吴宫花草埋幽径，\n" +
            "晋代衣冠成古邱。\n" +
            "三山半落青天外，\n" +
            "二水中分白鹭洲。\n" +
            "总为浮云能蔽日，\n" +
            "长安不见使人愁。\n" +
            "\n" +
            "宣州谢公楼饯别校书叔云\n" +
            "弃我去者昨日之日不可留，\n" +
            "乱我心者今日之日多烦忧。\n" +
            "长风万里送秋雁，\n" +
            "对此可以酣高楼。\n" +
            "蓬莱文章建安骨，\n" +
            "中间小谢又清发。\n" +
            "俱怀逸兴壮思飞，\n" +
            "欲上青天览日月。\n" +
            "抽刀断水水更流，\n" +
            "举杯消愁愁更愁。\n" +
            "人生在世不称意，\n" +
            "明朝散发弄扁舟。\n" +
            "\n" +
            "将进酒\n" +
            "君不见黄河之水天上来，奔流到海不复回；\n" +
            "君不见高堂明镜悲白发，朝如青丝暮成雪。\n" +
            "人生得意须尽欢，莫使金樽空对月。\n" +
            "天生我材必有用，千金散尽还复来。\n" +
            "烹羊宰牛且为乐，会须一饮三百杯。\n" +
            "岑夫子，丹丘生，\n" +
            "将进酒，君莫停。\n" +
            "与君歌一曲，\n" +
            "请君为我侧耳听。\n" +
            "钟鼓馔玉不足贵，但愿长醉不愿醒。\n" +
            "古来圣贤皆寂寞，惟有饮者留其名。\n" +
            "陈王昔时宴平乐，斗酒十千恣欢谑。\n" +
            "主人何为言少钱，径须沽取对君酌。\n" +
            "五花马，千金裘，\n" +
            "呼儿将出换美酒，与尔同消万古愁。\n" +
            "\n" +
            "行路难之一\n" +
            "\n" +
            "金樽清酒斗十千，玉盘珍馐直万钱。\n" +
            "停杯投箸不能食， 拔剑四顾心茫然。\n" +
            "欲渡黄河冰塞川，将登太行雪满山。\n" +
            "闲来垂钓坐溪上，忽复乘舟梦日边。\n" +
            "行路难，行路难， 多歧路，今安在？\n" +
            "长风破浪会有时，直挂云帆济沧海。";
    
    public static void main(String... args) {
        Encoder encoder = new Encoder(LI_BO);
        Decoder decoder = new Decoder(encoder.encode()) {
            
            @Override
            protected char beforeInsert(final char c, final int position) {
                return c; //can cipher here
            }
        };
        log.error('\n' + decoder.decode(encoder.size()));
        final TreeSet<Range> raw = new TreeSet<>();
        long lastRight = 0;
        for (char c = 0; c < Character.MAX_VALUE; ++c) {
            int i = new Random().nextInt(50000);
            long right = lastRight + i;
            raw.add(new Range(lastRight, right));
            lastRight = right;
        }
        final Ranges ranges = new Ranges(raw);
        final Iterator<Range> it = ranges.iterator();
        HashMaker hashMaker = new HashMaker(ranges, LI_BO) {
            
            @Override
            protected Range applyRule(final Character c) {
                checkArgument(it.hasNext(), "iterator has ended unexpectedly");
                return it.next();
            }
        };
        log.debug("\n  " + hashMaker.encode(LI_BO));
        log.debug('\n' + hashMaker.decode(hashMaker.encode(LI_BO)));
    }
}

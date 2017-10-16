/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import java.util.HashMap;

/**
 * Created by apple on 16/4/10.
 * This class sotred all the bbox of cities in SA2 level.
 */
public final class City_BBOX {

    public static HashMap<String,BBOX> city_bbox = new HashMap<>();

    static {
        city_bbox.put("Greater Sydney", new BBOX(149.9719, 151.6305, -34.3312, -32.9961));
        city_bbox.put("Capital Region", new BBOX(147.7108, 150.3791, -37.5051, -33.8878));
        city_bbox.put("Central Coast", new BBOX(150.9841, 151.6305, -33.5706, -33.0436));
        city_bbox.put("Central West", new BBOX(146.0540, 150.6198, -34.3172, -31.7787));
        city_bbox.put("Coffs Harbour", new BBOX(152.1683,153.3948,-30.5671,-28.9700));
        city_bbox.put("Far West and Orana", new BBOX(140.9995,150.1106,-33.3667,-28.9978));
        city_bbox.put("Hunter Valley exc Newcastle", new BBOX(149.7916,152.3369,-33.1390,-31.5537));
        city_bbox.put("Illawarra", new BBOX(150.5275,151.0662,-34.7922,-34.0934));
        city_bbox.put("Mid North Coast", new BBOX(151.4028,159.1092,-32.6158,-30.5006));
        city_bbox.put("Murray", new BBOX(141.0017,147.8192,-36.1299,-32.7485));
        city_bbox.put("Newcastle and Lake Macquarie", new BBOX(151.3315,151.8788,-33.2028,-32.7798));
        city_bbox.put("New England and North West", new BBOX(148.6762,152.6314,-31.8582,-28.2492));
        city_bbox.put("Riverina", new BBOX(144.8511,148.8088,-36.8061,-32.6713));
        city_bbox.put("Southern Highlands and Shoalhaven", new BBOX(149.9761,150.8498,-35.5684,-34.2125));
        city_bbox.put("Sydney - Baulkham Hills and Hawkesbury", new BBOX(150.3567,-33.7730,151.1556,-32.9961));
        city_bbox.put("Sydney - Blacktown", new BBOX(150.7596,150.9669,-33.8340,-33.6429));
        city_bbox.put("Sydney - City and Inner South", new BBOX(151.1366,151.2367,-33.9848,-33.8509));
        city_bbox.put("Sydney - Eastern Suburbs", new BBOX(151.2112,151.2878,-34.0018,-33.8325));
        city_bbox.put("Sydney - Inner South West", new BBOX(151.1366,151.2367,-33.9848,-33.8509));
        city_bbox.put("Sydney - Inner West", new BBOX(151.0567,151.1966,-33.9163,-33.8228));
        city_bbox.put("Sydney - Northern Beaches", new BBOX(151.1608,151.3430,-33.8239,-33.5719));
        city_bbox.put("Sydney - North Sydney and Hornsby", new BBOX(151.0579,151.2689,-33.8536,-33.5073));
        city_bbox.put("Sydney - Outer South West", new BBOX(150.4183,150.9967,-34.3312,-33.9408));
        city_bbox.put("Sydney - Outer West and Blue Mountains", new BBOX(149.9719,150.8412,-34.3083,-33.4857));
        city_bbox.put("Sydney - Parramatta", new BBOX(150.9124,151.0841,-33.8990,-33.7571));
        city_bbox.put("Sydney - Ryde", new BBOX(151.0375,151.1803,-33.8449,-33.7192));
        city_bbox.put("Sydney - South West", new BBOX(150.6179,150.9981,-34.0510,-33.8207));
        city_bbox.put("Sydney - Sutherland", new BBOX(150.9366,151.2319,-34.1723,-33.9772));
        city_bbox.put("Greater Darwin", new BBOX(130.8151, 131.3967, -12.8619, -12.0010));
        city_bbox.put("Northern Territory - Outback", new BBOX(129.0004,138.0012,-25.9995,-10.9659));
        city_bbox.put("Greater Brisbane", new BBOX(152.0734, 153.5467, -28.3640, -26.4519));
        city_bbox.put("Brisbane - East", new BBOX(153.0879,153.5467,-27.7408,-27.0220));
        city_bbox.put("Brisbane Inner City", new BBOX(152.9583,153.0896,-27.4938,-27.4040));
        city_bbox.put("Brisbane - North", new BBOX(152.9752,153.1605,-27.4457,-27.2787));
        city_bbox.put("Brisbane - South", new BBOX(152.9693,153.1918,-27.6604,-27.4576));
        city_bbox.put("Brisbane - West", new BBOX(152.7986,153.0206,-27.6021,-27.3933));
        city_bbox.put("Cairns", new BBOX(144.7408,146.3587,-18.5783,-15.9028));
        city_bbox.put("Darling Downs - Maranoa", new BBOX(146.8631,152.4926,-29.1779,-24.8789));
        city_bbox.put("Fitzroy", new BBOX(146.5725,152.7184,-25.9661,-21.9152));
        city_bbox.put("Gold Coast", new BBOX(153.0079,153.5522,-28.3579,-27.6918));
        city_bbox.put("Ipswich", new BBOX(152.1340,152.9984,-28.3390,-26.9902));
        city_bbox.put("Logan - Beaudesert", new BBOX(152.6478,153.2905,-28.3640,-27.5873));
        city_bbox.put("Mackay", new BBOX(146.0315,150.4420,-23.5560,-19.7055));
        city_bbox.put("Moreton Bay - North", new BBOX(152.0734,153.2076,-27.2635,-26.4519));
        city_bbox.put("Moreton Bay - South", new BBOX(152.6810,153.0779,-27.4224,-27.0862));
        city_bbox.put("Queensland - Outback", new BBOX(137.9960,147.9553,-28.9991,-9.1422));
        city_bbox.put("Sunshine Coast", new BBOX(152.5509,153.1514,-26.9848,-26.1371));
        city_bbox.put("Toowoomba", new BBOX(151.7665,152.3823,-27.9697,-27.3493));
        city_bbox.put("Townsville", new BBOX(144.2852,147.6633,-22.1048,-18.3137));
        city_bbox.put("Wide Bay", new BBOX(150.3696,153.3604,-26.9479,-24.3921));
        city_bbox.put("Greater Adelaide", new BBOX(138.4362, 139.0440, -35.3503, -34.5002));
        city_bbox.put("Adelaide - Central and Hills", new BBOX(138.5719,139.0440,-35.2433,-34.6805));
        city_bbox.put("Adelaide - North", new BBOX(138.4362,138.8480,-34.8883,-34.5002));
        city_bbox.put("Adelaide - South", new BBOX(138.4421,138.7134,-35.3503,-34.9585));
        city_bbox.put("Adelaide - West", new BBOX(138.4757,138.5879,-34.9759,-34.7552));
        city_bbox.put("Barossa - Yorke - Mid North", new BBOX(136.4414,139.3580,-35.3782,-32.1203));
        city_bbox.put("South Australia - Outback", new BBOX(129.0013,141.0030,-35.3404,-25.9961));
        city_bbox.put("South Australia - South East", new BBOX(136.5329,140.9739,-38.0626,-33.8008));
        city_bbox.put("Greater Hobart", new BBOX(147.0267, 147.9369, -43.1213, -42.6554));
        city_bbox.put("Launceston and North East", new BBOX(145.9513,148.4987,-42.2813,-39.2037));
        city_bbox.put("South East", new BBOX(145.8325,148.3593,-43.7405,-41.7003));
        city_bbox.put("West and North West", new BBOX(143.8189,146.7621,-43.3226,-39.5793));
        city_bbox.put("Greater Melbourne", new BBOX(144.9514, 144.9901, -37.8555, -37.7997));
        city_bbox.put("Ballarat", new BBOX(143.0630,144.4906,-37.9885,-36.6988));
        city_bbox.put("Bendigo", new BBOX(143.3152,144.8536,-37.4589,-35.9059));
        city_bbox.put("Geelong", new BBOX(143.6221,-144.7202,-38.5794,-37.7812));
        city_bbox.put("Hume", new BBOX(144.5304,148.2207,-37.8280,-35.9285));
        city_bbox.put("Melbourne Inner", new BBOX(144.8889,145.0453,-37.8917,-37.7325));
        city_bbox.put("Melbourne Inner East", new BBOX(144.9993,145.1841,-37.8759,-37.7339));
        city_bbox.put("Melbourne Inner South", new BBOX(144.9834,145.1563,-38.0850,-37.8374));
        city_bbox.put("Melbourne North East", new BBOX(144.8807,145.5800,-37.7851,-37.2629));
        city_bbox.put("Melbourne North West", new BBOX(144.4577,144.9853,-37.7761,-37.1751));
        city_bbox.put("Melbourne Outer East", new BBOX(145.1569,145.8784,-37.9750,-37.5260));
        city_bbox.put("Melbourne South East", new BBOX(145.0795,145.7651,-38.3325,-37.8533));
        city_bbox.put("Melbourne West", new BBOX(144.3336,144.9165,-38.0046,-37.5464));
        city_bbox.put("Shepparton", new BBOX(144.2593,146.2465,-36.7626,-35.8020));
        city_bbox.put("North West", new BBOX(140.9617,144.4182,-37.8366,-33.9804));
        city_bbox.put("Mornington Peninsula", new BBOX(144.6514,145.2617,-38.5030,-38.0674));
        city_bbox.put("Warrnambool and South West", new BBOX(140.9657,143.9461,-38.8577,-37.0870));
        city_bbox.put("Latrobe Gippsland", new BBOX(145.1094,149.9767,-39.1592,-36.6124));
        city_bbox.put("Greater Perth", new BBOX(115.4495, 116.4151, -32.8019, -31.4551));
        city_bbox.put("Bunbury", new BBOX(114.9746,116.8568,-35.0689,-32.7552));
        city_bbox.put("Mandurah", new BBOX(115.6068,116.0315,-32.8019,-32.4446));
        city_bbox.put("Perth - Inner", new BBOX(115.7500,115.8934,-32.0251,-31.9075));
        city_bbox.put("Perth - North East", new BBOX(115.8769,116.4151,-32.0626,-31.5972));
        city_bbox.put("Perth - North West", new BBOX(115.5607,115.8999,-31.9330,-31.4551));
        city_bbox.put("Perth - South East", new BBOX(115.8266,116.3581,-32.4775,-31.9159));
        city_bbox.put("Perth - South West", new BBOX(115.4495,115.9162,-32.4582,-31.9873));
        city_bbox.put("Western Australia - Outback", new BBOX(112.9211,129.0019,-34.4747,-13.6895));
        city_bbox.put("Western Australia - Wheat Belt", new BBOX(114.9704,120.5815,-35.1348,-29.6123));
        city_bbox.put("Australian Capital Territory", new BBOX(148.7627, 149.3993, -35.9208, -35.1245));
    }
}

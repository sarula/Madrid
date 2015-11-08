/*
*	全国三级城市联动 js版
*/
function Dsy(){
	this.Items = {};
}
Dsy.prototype.add = function(id,iArray){
	this.Items[id] = iArray;
}
Dsy.prototype.Exists = function(id){
	if(typeof(this.Items[id]) == "undefined") return false;
	return true;
}

function change(v){
	var str="0";
	for(i=0;i<v;i++){
		str+=("_"+(document.getElementById(s[i]).selectedIndex-1));
	};
	var ss=document.getElementById(s[v]);
	with(ss){
		length = 0;
		options[0]=new Option(opt0[v],opt0[v]);
		if(v && document.getElementById(s[v-1]).selectedIndex>0 || !v){
			if(dsy.Exists(str)){
				ar = dsy.Items[str];
				for(i=0;i<ar.length;i++){
					options[length]=new Option(ar[i],ar[i]);
				}//end for
				if(v){ options[0].selected = true; }
			}
		}//end if v
		if(++v<s.length){change(v);}
	}//End with
}

var dsy = new Dsy();

dsy.add("0",["通识课本","专业课本","休闲读物"]);
dsy.add("0_0_0",["高等数学","大学英语","线性代数","概率论与数理统计"," VISUAL BASIC "," C++ ","其他"]);
dsy.add("0_0",["通识课本"]);

dsy.add("0_1_0",["采矿工程","工业工程","交通运输"]);
dsy.add("0_1_1",["消防工程","安全工程"]);
dsy.add("0_1_2",["土木工程","工程力学","建筑学","建筑环境与能源应用工程","工程管理"]);
dsy.add("0_1_3",["机械工程及自动化","测试技术与仪器"]);
dsy.add("0_1_4",["电气工程及其自动化","信息工程","电子科学与技术"]);
dsy.add("0_1_5",["地质工程","水文与水资源工程","地球物理学","资源环境与城乡规划管理","煤及煤层气工程"]);
dsy.add("0_1_6",["矿物加工工程","化学工程与工艺","应用化学","生物工程","过程装备与控制工程"]);
dsy.add("0_1_7",["测绘科学与技术","环境科学与工程","土地资源管理"]);
dsy.add("0_1_8",["能源与动力工程"]);
dsy.add("0_1_9",["材料科学与工程","材料成型与控制工程"]);
dsy.add("0_1_10",["数学","物理学","统计学"]);
dsy.add("0_1_11",["计算机科学与技术","电子信息科学与技术","信息安全","网络工程"]);
dsy.add("0_1_12",["电子商务","工商管理","国际经济与贸易","金融学","会计学","人力资源管理","市场营销"]);
dsy.add("0_1_13",["行政管理","汉语言文学","法学","广播电视新闻学","社会工作"]);
dsy.add("0_1_14",["英语","德语"]);
dsy.add("0_1_15",["环境设计","工业设计","音乐学"]);
dsy.add("0_1_16",["社会体育","体育教育"]);
dsy.add("0_1_17",["电气工程与自动化","土木工程","商学","机械工程及自动化"]);


dsy.add("0_1",["矿业学院","安全学院","力建学院","机电学院","信电学院","资源学院","化工学院","环测学院","电力学院","材料学院","理学院","计算机学院","管理学院","文法学院","外文学院","艺术学院","体育学院","国际学院"]);

dsy.add("0_2_0",["文学","杂志"]);
dsy.add("0_2",["休闲读物"]);



dsy.add("0",["通识课本","专业课本","休闲读物"]);

var s=["s_province","s_city","s_county"];//三个select的name
var opt0 = ["请选择...","请选择...","请选择..."];//初始值
function _init_area(){  //初始化函数
	for(i=0;i<s.length-1;i++){
	  document.getElementById(s[i]).onchange=new Function("change("+(i+1)+")");
	}
	change(0);
}

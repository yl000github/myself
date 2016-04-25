require("ymt.jsse.FileEnabler");
var fileEnabler=ymt.jsse.FileEnabler;
require("ymt.jsse.ConfigEnabler");
var config = ymt.jsse.ConfigEnabler.readPropertyConfig("config.properties");
var path=config.getProperty("sangong-brain");
var txt="hello";
fileEnabler.saveContent2File(path,txt);
$_response_$={
		content:"写入成功"
}
function createColumn() {
    return jQuery("<td></td>");
}
function createRow() {
    return jQuery("<tr></tr>");
}
function createEle(tag){
    return jQuery("<"+tag+"></"+tag+">");
}
function reload(){
	window.location.reload();
} 

function toURL(url){
	window.location.href=url;
}

function isString(obj){
	return typeof(obj) == "string";
}

function isObject(obj){
	return typeof(obj) == "object";
}

function fillSelect(select, data, valueKey, textKey){

    var $select = isString(select) ? jQuery(select) : select;

    $select.empty();

    jQuery.each(data, function(i, item){

        var value = (!isString(item)) ? item[valueKey] : item;
        var text = (!isString(item)) ? item[textKey] : item;

        var $op = createEle("option").appendTo($select);
        $op.text(text).val(value);

    })
}


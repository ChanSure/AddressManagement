function add(){
    const ret = document.createElement("form")
    ret.action = "/add"
    ret.method = "post"

    document.body.appendChild(ret)
    ret.submit()

    return ret
}

function update(elem) {
    const row = elem.parentNode.parentNode.rowIndex - 1;

    const ret = document.createElement("form");
    ret.action = "/update"
    ret.method = "post"

    const temp = document.createElement("textarea");
    temp.name = "row"
    temp.value = row.toString()
    ret.appendChild(temp)
    document.body.appendChild(ret)

    ret.submit()
    return temp
}

function del(elem) {
    const row = elem.parentNode.parentNode.rowIndex - 1;
    const tr = elem.parentNode.parentNode;
    const tbody = tr.parentNode;
    tbody.removeChild(tr)   //delete

    const xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = callBack
    xmlHttpRequest.open("post", "/del", true)
    xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")    //表单数据
    xmlHttpRequest.send("row=" + row)
}
function callBack() {}
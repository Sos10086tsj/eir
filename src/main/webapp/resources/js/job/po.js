eir.po={
	uploadPo : function(){
		$('#js_po_upload_iframe').off('load');
		$('#js_po_upload_iframe').on('load', eir.po.uploadPoFrameLoad);
		$('#js_po_upload_form').submit();
	},
	uploadPoFrameLoad : function(){
		var response = $("#js_po_upload_iframe").contents().text();
		console.info("response:" + response);
		return false;
	}
	
}
$(function(){
	eir.amezeui.file.initSigleFileUpload($('#doc-form-file'),$('#file-list'));
})
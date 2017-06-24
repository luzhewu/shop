package cn.shop.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		/**
		 * 第一种方式：使用WebDataBinder注册一个自定义的编辑器，编辑器是日期类型
		 * 使用自定义的日期编辑器，日期格式：yyyy-MM-dd,第二个参数为是否为空   true代表可以为空
		 */
		/*binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));*/

		/**
		 * 方式二：使用WebDataBinder注册一个自定义的编辑器，编辑器是日期类型
		 * 使用属性编辑器实现：重载setAsText,getAsText
		 */
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

			@Override
			public String getAsText() {
				return new SimpleDateFormat("yyyy-MM-dd")
						.format((Date) getValue());
			}

			@Override
			public void setAsText(String text) {
				try {
					setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
				} catch (Exception e) {
					e.printStackTrace();
					setValue(null);
				}
			}
		});
	}
}

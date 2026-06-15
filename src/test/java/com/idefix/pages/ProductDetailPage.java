package com.idefix.pages;

import com.idefix.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {

    private final By productName = By.cssSelector("h1");
    private final By addToCartButton = By.xpath("//button[contains(., 'Sepete') or contains(., 'Sepete Ekle')]");
    private final By addToCartModal = By.cssSelector("div[data-testid='modal']");
    private final By goToCartButton = By.xpath("//div[@data-testid='modal']//button[normalize-space()='Sepete Git']");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPriceText() {
        findVisible(productName);
        String script = "const pricePattern = /\\d{1,3}(?:\\.\\d{3})*,\\d{2}\\s*TL/;"
                + "const title = document.querySelector('h1');"
                + "if (!title) return '';"
                + "const titleRect = title.getBoundingClientRect();"
                + "const hasPricedChild = element => Array.from(element.children || [])"
                + ".some(child => pricePattern.test((child.textContent || '').trim()));"
                + "const isMainProductArea = rect => rect.left >= titleRect.left - 30"
                + "&& rect.left <= titleRect.left + 650"
                + "&& rect.top >= titleRect.bottom"
                + "&& rect.top <= titleRect.bottom + 240;"
                + "return Array.from(document.querySelectorAll('body *'))"
                + ".filter(element => {"
                + "const style = window.getComputedStyle(element);"
                + "const rect = element.getBoundingClientRect();"
                + "const text = (element.textContent || '').trim();"
                + "return style.visibility !== 'hidden' && style.display !== 'none'"
                + "&& rect.width > 0 && rect.height > 0"
                + "&& isMainProductArea(rect)"
                + "&& pricePattern.test(text)"
                + "&& !hasPricedChild(element)"
                + "&& !/Alışveriş Kredisi|Peşin Fiyatına|Taksit|Garanti/i.test(text);"
                + "})"
                + ".map(element => {"
                + "const text = (element.textContent || '').trim().replace(/\\s+/g, ' ');"
                + "return {"
                + "text: text.match(pricePattern)[0],"
                + "fontSize: parseFloat(window.getComputedStyle(element).fontSize) || 0,"
                + "top: element.getBoundingClientRect().top"
                + "};"
                + "})"
                + ".sort((first, second) => second.fontSize - first.fontSize || first.top - second.top)"
                + ".map(item => item.text)[0] || '';";
        String priceText = (String) ((JavascriptExecutor) driver).executeScript(script);
        if (priceText == null || priceText.isBlank()) {
            throw new IllegalStateException("Product price could not be found.");
        }
        return priceText;
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void goToCartFromModal() {
        findVisible(addToCartModal);
        click(goToCartButton);
    }
}

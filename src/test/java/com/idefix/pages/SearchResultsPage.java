package com.idefix.pages;

import com.idefix.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchResultsPage extends BasePage {

    private final By productGrid = By.cssSelector("section.grid");
    private final By productLinks = By.cssSelector("section.grid a[href*='-p-']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyProductsAreListed() {
        findVisible(productGrid);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productLinks, 0));
    }

    public void selectProductByOrder(int productOrder) {
        verifyProductsAreListed();

        List<String> productHrefs = getProductHrefs();
        Map<String, String> uniqueProductLinks = new LinkedHashMap<>();
        for (String href : productHrefs) {
            if (href != null && href.contains("-p-") && !href.contains("#sortId")) {
                uniqueProductLinks.putIfAbsent(href, href);
            }
        }

        if (productOrder < 1 || productOrder > uniqueProductLinks.size()) {
            throw new IllegalArgumentException("Product order is out of range: " + productOrder);
        }

        String selectedProductUrl = uniqueProductLinks.values().stream()
                .skip(productOrder - 1L)
                .findFirst()
                .orElseThrow();

        driver.navigate().to(selectedProductUrl);
    }

    @SuppressWarnings("unchecked")
    private List<String> getProductHrefs() {
        String script = "const grid = document.querySelector('section.grid');"
                + "if (!grid) { return []; }"
                + "return Array.from(grid.querySelectorAll(\"a[href*='-p-']\"))"
                + ".map(anchor => anchor.href)"
                + ".filter(Boolean);";
        return (List<String>) ((JavascriptExecutor) driver).executeScript(script);
    }
}

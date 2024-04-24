package s24.budgetmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import s24.budgetmanager.domain.PurchaseRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

/**
 * Test class for testing the BudgetmanagerApplication.
 */
@SpringBootTest
class BudgetmanagerApplicationTests {

    @Autowired
    private BudgetmanagerApplication application;

    @MockBean
    private PurchaseRepository purchaseRepository;

    @Autowired
    private MockMvc mockMvc;

    // Test method to verify if the application context loads successfully.
    @Test
    public void contextLoads() {
        assertThat(application).isNotNull();
    }

    // Test method to verify if all purchases can be retrieved.
    @Test
    public void testGetAllPurchases() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/purchases"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Pen"));
    }

    // Test method to add a new purchase and verify its addition.
    @Test
    public void testAddPurchase() throws Exception {
        LocalDateTime automaticDate = LocalDateTime.now();
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"Pen\", \"price\": 23, \"comment\": \"\", \"purchaseDateTime\": \"" + automaticDate.toString() + "\", \"category\": {\"id\": 1, \"name\": \"Category1\"}, \"budget\": {\"id\": 1, \"name\": \"Budget1\"} }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Test method to delete a purchase by its ID and verify its deletion.
    @Test
    public void testDeletePurchaseById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

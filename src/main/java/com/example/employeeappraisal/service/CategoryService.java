// package com.example.employeeappraisal.service;

// import com.example.employeeappraisal.entity.Category;
// import com.example.employeeappraisal.entity.Employee;
// import com.example.employeeappraisal.repository.CategoryRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.*;

// @Service
// public class CategoryService {

//     private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

//     // Define constants for category distribution percentages
//     private static final double CATEGORY_A_PERCENT = 0.10;
//     private static final double CATEGORY_B_PERCENT = 0.20;
//     private static final double CATEGORY_C_PERCENT = 0.40;
//     private static final double CATEGORY_D_PERCENT = 0.20;

//     @Autowired
//     private CategoryRepository categoryRepository;

//     /**
//      * Categorize employees by ratings using a bell curve distribution.
//      *
//      * @param employees List of all employees.
//      * @return Map of categories to their respective employees.
//      */
//     public Map<String, List<Employee>> categorizeEmployeesByRatings(List<Employee> employees) {
//         if (employees.isEmpty()) {
//             logger.warn("Employee list is empty. No categorization performed.");
//             return Collections.emptyMap();
//         }

//         // Sort employees in descending order of rating
//         employees.sort(Comparator.comparingInt(Employee::getRating).reversed());

//         int totalEmployees = employees.size();

//         // Define distribution for each category
//         Map<String, Integer> categoryDistribution = new LinkedHashMap<>();
//         categoryDistribution.put("A", (int) Math.round(totalEmployees * CATEGORY_A_PERCENT));
//         categoryDistribution.put("B", (int) Math.round(totalEmployees * CATEGORY_B_PERCENT));
//         categoryDistribution.put("C", (int) Math.round(totalEmployees * CATEGORY_C_PERCENT));
//         categoryDistribution.put("D", (int) Math.round(totalEmployees * CATEGORY_D_PERCENT));
//         categoryDistribution.put("E",
//                 totalEmployees - categoryDistribution.values().stream().mapToInt(Integer::intValue).sum());

//         // Assign employees to categories
//         Map<String, List<Employee>> categorizedEmployees = new LinkedHashMap<>();
//         int startIndex = 0;
//         for (Map.Entry<String, Integer> entry : categoryDistribution.entrySet()) {
//             int endIndex = Math.min(startIndex + entry.getValue(), totalEmployees);
//             categorizedEmployees.put(entry.getKey(), employees.subList(startIndex, endIndex));
//             startIndex = endIndex;
//         }

//         logger.info("Employees categorized into {} categories.", categoryDistribution.size());
//         return categorizedEmployees;
//     }

//     /**
//      * Calculate dynamic percentages for each category based on performance weights.
//      *
//      * @param categorizedEmployees Map of categorized employees.
//      * @return Map of category names to their calculated percentages.
//      */
//     public Map<String, Double> calculateCategoryPercentages(Map<String, List<Employee>> categorizedEmployees) {
//         Map<String, Integer> basePerformanceFactors = Map.of(
//                 "A", 5, // Highest weight for top performers
//                 "B", 4,
//                 "C", 3,
//                 "D", 2,
//                 "E", 1 // Lowest weight for bottom performers
//         );

//         double totalWeight = categorizedEmployees.entrySet().stream()
//                 .mapToDouble(entry -> basePerformanceFactors.get(entry.getKey()) * entry.getValue().size())
//                 .sum();

//         Map<String, Double> categoryPercentages = new LinkedHashMap<>();
//         categorizedEmployees.forEach((category, employees) -> {
//             double weight = basePerformanceFactors.get(category) * employees.size();
//             double percentage = (weight / totalWeight) * 100; // Normalize to total 100%
//             categoryPercentages.put(category, percentage);
//         });

//         logger.info("Category percentages calculated successfully.");
//         return categoryPercentages;
//     }

//     /**
//      * Update actual percentages in the database and assign improvement percentages
//      * to employees.
//      *
//      * @param categorizedEmployees Map of categorized employees.
//      */
//     public void updateActualPercentagesAndImprovements(Map<String, List<Employee>> categorizedEmployees) {
//         int totalEmployees = categorizedEmployees.values().stream()
//                 .mapToInt(List::size)
//                 .sum();

//         if (totalEmployees == 0) {
//             logger.warn("Total employees count is zero. Skipping update.");
//             return;
//         }

//         // Calculate dynamic category percentages
//         Map<String, Double> categoryPercentages = calculateCategoryPercentages(categorizedEmployees);

//         // Update the database and assign percentage improvements
//         categorizedEmployees.forEach((category, employeesInCategory) -> {
//             double actualPercentage = (employeesInCategory.size() / (double) totalEmployees) * 100;

//             // Find category entity and update its actual percentage
//             categoryRepository.findById(category.charAt(0)).ifPresentOrElse(
//                     categoryEntity -> {
//                         // Update the actual percentage
//                         categoryEntity.setActualPercentage(actualPercentage);
//                         categoryRepository.save(categoryEntity);

//                         logger.info("Updated actual percentage for category {}: {}", category, actualPercentage);
//                     },
//                     () -> logger.error("Category not found: {}", category));
//         });

//         logger.info("Category actual percentages updated successfully.");
//     }
// }

package com.example.employeeappraisal.service;

import com.example.employeeappraisal.entity.Category;
import com.example.employeeappraisal.entity.Employee;
import com.example.employeeappraisal.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    // Define constants for category distribution percentages
    private static final double CATEGORY_A_PERCENT = 0.10;
    private static final double CATEGORY_B_PERCENT = 0.20;
    private static final double CATEGORY_C_PERCENT = 0.40;
    private static final double CATEGORY_D_PERCENT = 0.20;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Categorize employees by ratings using a bell curve distribution.
     *
     * @param employees List of all employees.
     * @return Map of categories to their respective employees.
     */
    public Map<String, List<Employee>> categorizeEmployeesByRatings(List<Employee> employees) {
        if (employees.isEmpty()) {
            logger.warn("Employee list is empty. No categorization performed.");
            return Collections.emptyMap();
        }

        // Sort employees in descending order of rating
        employees.sort(Comparator.comparingInt(Employee::getRating).reversed());

        int totalEmployees = employees.size();

        // Define distribution for each category
        Map<String, Integer> categoryDistribution = new LinkedHashMap<>();
        categoryDistribution.put("A", (int) Math.round(totalEmployees * CATEGORY_A_PERCENT));
        categoryDistribution.put("B", (int) Math.round(totalEmployees * CATEGORY_B_PERCENT));
        categoryDistribution.put("C", (int) Math.round(totalEmployees * CATEGORY_C_PERCENT));
        categoryDistribution.put("D", (int) Math.round(totalEmployees * CATEGORY_D_PERCENT));
        categoryDistribution.put("E",
                totalEmployees - categoryDistribution.values().stream().mapToInt(Integer::intValue).sum());

        // Assign employees to categories
        Map<String, List<Employee>> categorizedEmployees = new LinkedHashMap<>();
        int startIndex = 0;
        for (Map.Entry<String, Integer> entry : categoryDistribution.entrySet()) {
            int endIndex = Math.min(startIndex + entry.getValue(), totalEmployees);
            categorizedEmployees.put(entry.getKey(), employees.subList(startIndex, endIndex));
            startIndex = endIndex;
        }

        logger.info("Employees categorized into {} categories.", categoryDistribution.size());
        return categorizedEmployees;
    }

    /**
     * Update actual percentages in the database.
     *
     * @param categorizedEmployees Map of categorized employees.
     */
    public void updateActualPercentagesAndImprovements(Map<String, List<Employee>> categorizedEmployees) {
        int totalEmployees = categorizedEmployees.values().stream()
                .mapToInt(List::size)
                .sum();

        if (totalEmployees == 0) {
            logger.warn("Total employees count is zero. Skipping update.");
            return;
        }

        // Update the database with actual percentages
        categorizedEmployees.forEach((category, employeesInCategory) -> {
            double actualPercentage = (employeesInCategory.size() / (double) totalEmployees) * 100;

            // Find category entity and update its actual percentage
            categoryRepository.findById(category.charAt(0)).ifPresentOrElse(
                    categoryEntity -> {
                        // Update the actual percentage
                        categoryEntity.setActualPercentage(actualPercentage);
                        categoryRepository.save(categoryEntity);

                        logger.info("Updated actual percentage for category {}: {}", category, actualPercentage);
                    },
                    () -> logger.error("Category not found: {}", category));
        });

        logger.info("Category actual percentages updated successfully.");
    }
}

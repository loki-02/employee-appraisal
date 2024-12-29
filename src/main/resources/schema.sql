CREATE TABLE IF NOT EXISTS Employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(255),
    rating CHAR(1)
);
CREATE TABLE IF NOT EXISTS Category (
    category CHAR(1) PRIMARY KEY,  -- A single character for the category (A, B, C, D, E)
    standard_percentage DECIMAL(5, 2), -- Standard percentage with precision (e.g., 10.00%)
    actual_percentage DECIMAL(5, 2)   -- Actual percentage with precision (initially NULL)
);
CREATE TABLE IF NOT EXISTS PerformanceLevel (
    level_name VARCHAR(50) PRIMARY KEY, -- Performance level name (e.g., Outstanding, Very Good)
    category CHAR(1) NOT NULL           -- Category corresponding to the level (A, B, C, D, E)
);

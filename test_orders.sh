#!/bin/bash

# Base URLs
REST_BASE_URL="http://localhost:8080/rest"
RPC_BASE_URL="http://localhost:8080/restrpc"

# Order endpoint
ORDER_URL="$REST_BASE_URL/orders"

# JSON payload for placing an order
ORDER_PAYLOAD='{
  "address": "123 Example Street",
  "mealIds": ["5268203c-de76-4921-a3e3-439db69c462a", "4237681a-441f-47fc-a747-8e0169bacea1"]
}'

echo "Placing an order to $ORDER_URL..."
echo "Payload: $ORDER_PAYLOAD"
ORDER_RESPONSE=$(curl -s -X POST "$ORDER_URL" \
     -H "Content-Type: application/json" \
     -d "$ORDER_PAYLOAD")

echo "Order Response: $ORDER_RESPONSE"
echo ""

# Fetch all meals - REST
echo "Fetching all meals from REST endpoint..."
REST_MEALS=$(curl -s -X GET "$REST_BASE_URL/meals" -H "Accept: application/json")
echo "REST Meals: $REST_MEALS"
echo ""

# Fetch all meals - RPC
echo "Fetching all meals from RPC-style endpoint..."
RPC_MEALS=$(curl -s -X GET "$RPC_BASE_URL/meals" -H "Accept: application/json")
echo "RPC Meals: $RPC_MEALS"
echo ""

# Get the cheapest meal - REST
echo "Fetching the cheapest meal from REST endpoint..."
CHEAPEST_MEAL_REST=$(curl -s -X GET "$REST_BASE_URL/meals/cheapest" -H "Accept: application/json")
echo "Cheapest Meal (REST): $CHEAPEST_MEAL_REST"
echo ""

# Get the largest meal - REST
echo "Fetching the largest meal from REST endpoint..."
LARGEST_MEAL_REST=$(curl -s -X GET "$REST_BASE_URL/meals/largest" -H "Accept: application/json")
echo "Largest Meal (REST): $LARGEST_MEAL_REST"
echo ""

# Repeat fetching the cheapest and largest meals for RPC if needed
# Example for fetching the cheapest meal - RPC
echo "Fetching the cheapest meal from RPC-style endpoint..."
CHEAPEST_MEAL_RPC=$(curl -s -X GET "$RPC_BASE_URL/meals/cheapest" -H "Accept: application/json")
echo "Cheapest Meal (RPC): $CHEAPEST_MEAL_RPC"
echo ""

# Example for fetching the largest meal - RPC
echo "Fetching the largest meal from RPC-style endpoint..."
LARGEST_MEAL_RPC=$(curl -s -X GET "$RPC_BASE_URL/meals/largest" -H "Accept: application/json")
echo "Largest Meal (RPC): $LARGEST_MEAL_RPC"

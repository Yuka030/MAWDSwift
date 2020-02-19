//
//  QueensSolver.swift
//  SwiftAGDS
//
//  Created by Derrick Park on 2019-03-13.
//  Copyright © 2019 Derrick Park. All rights reserved.
//

import Foundation

/// Write a function solveQueens that accepts a Board as a parameter
/// and tries to place 8 queens on it safely.
///
/// - Your method should stop exploring if it finds a solution
/// - You are allowed to change the function header (args or return type)
/// - Your total recursive calls should not exceed 120 times.

var count = 0
var total = 0

// 1. Print all possible ways to place 8 queens on 8 * 8 chessboard
func solveQueens(board: inout Board, col: Int) {
	count += 1
    
    //base case
    if col >= board.size {
        printSolution(board)
        total += 1
        return
    }
    
    //recursive case
    for i in 0..<board.size {
        if(board.isSafe(row: i, col: col)){
            board.place(row: i, col: col)
            solveQueens(board: &board, col: col + 1)
            board.remove(row: i, col: col)
        }
    }
}

// 2. Print the first possible way to place 8 queens on 8 * 8 chessboard
func solveQueensFirst(board: inout Board, col: Int)->Bool {
    count += 1
    
    //base case
    if col >= board.size {
        printSolution(board)
        total += 1
        return true
    }
    
    //recursive case
    for i in 0..<board.size {
        if(board.isSafe(row: i, col: col)){
            board.place(row: i, col: col)
            
            if(solveQueensFirst(board: &board, col: col + 1)){
                return true
            }
            
            board.remove(row: i, col: col)
        }
    }
    return false
}

func printSolution(_ board: Board){
    print(board.description)
}

func printCountAndTotal(){
    print("# of recursive calls: \(count)")
    print("# of solutions: \(total)")
}


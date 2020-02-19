//
//  main.swift
//  NQueenProblem
//
//  Created by Yuka Ishiwata on 2020-02-19.
//  Copyright © 2020 Yuka. All rights reserved.
//

import Foundation

var board1 = Board(size: 8)
//solveQueens(board:&board1, col:0)
//printCountAndTotal()

solveQueensFirst(board: &board1, col: 0)
printCountAndTotal()

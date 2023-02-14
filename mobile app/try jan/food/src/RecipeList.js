import React from 'react'
import Racipe from './Racipe'

export default function RecipeList({ fooditem }) {
    return (
        <div>
            {fooditem.map(item => {
                return  (
                    <Racipe
                        key={item.id}
                        {...item}
                    />
                )
            })}
        </div>
    )
}

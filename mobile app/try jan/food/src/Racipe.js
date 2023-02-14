import React from 'react'

export default function Racipe(props) {
    const {
        name,
        Cooktime,
        servings,
        Instruction
    } = props;
    
    return (
        <>
            <div>
                <h3>{name}</h3>
                <div>
                    <button>Edit</button>
                    <button>Delete</button>
                </div>
            </div>
            <div>
                <span>Cook Time :</span>
                <span>{Cooktime}</span>
            </div>
            <div>
                <span>Serving :</span>
                <span>{servings}</span>
            </div>
            <div>
                <span>Instruction :</span>
                <div>{Instruction}
                </div>
            </div>
        </>
    )
}

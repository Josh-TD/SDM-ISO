import React from "react";

export const FileTableCheckbox = React.forwardRef(({ indeterminate, ...rest }, ref) => {
    const defaultRef = React.useRef();
    const resolvedRef = ref || defaultRef;

    React.useEffect(() => {
    resolvedRef.current.indeterminate = indeterminate;
    }, [resolvedRef, indeterminate]);

    const handleClick = (e) => {
        e.stopPropogation();
        props.onClick(e);
    };
    return (
        <>
            <input type="checkbox" ref={resolvedRef} {...rest} />
        </>
    );
})